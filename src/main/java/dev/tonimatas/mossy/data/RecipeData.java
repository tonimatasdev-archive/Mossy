package dev.tonimatas.mossy.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.tonimatas.mossy.Mossy;
import dev.tonimatas.mossy.launcher.Main;
import dev.tonimatas.mossy.logger.Logger;
import dev.tonimatas.mossy.util.RecipeUtils;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.server.play.DeclareRecipesPacket;
import net.minestom.server.recipe.*;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@SuppressWarnings({"DataFlowIssue", "SimplifiableConditionalExpression"})
public class RecipeData {
    private static final String recipesFolder = "data/minecraft/recipes";
    
    public static void init() {
        List<String> recipes = new ArrayList<>();
        
        JarFile jarFile;
        
        try {
            jarFile = new JarFile(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()));
        } catch (Exception e) {
            Logger.error("Error on parse recipes");
            return;
        }

        for (Iterator<JarEntry> it = jarFile.entries().asIterator(); it.hasNext(); ) {
            String entry = it.next().getName();

            if (entry.startsWith(recipesFolder)) recipes.add(entry);
        }
        
        for (String recipe : recipes) {
            InputStream inputStream = Main.class.getResourceAsStream("/" + recipe);
            if (inputStream == null) continue;
            String recipeId = "minecraft:" + recipe.split("/")[recipe.split("/").length - 1].replace(".json", "");
            Scanner scanner = new Scanner(inputStream);
            
            StringBuilder string = new StringBuilder();
            while (scanner.hasNext()) {
                string.append(scanner.nextLine());
            }
            
            scanner.close();
            String jsonString = string.toString();
            
            if (jsonString.contains("tag")) {
                if (Mossy.debug) Logger.error("The recipe " + recipeId + " has tag. Unsupported at the moment.");
                continue;
            }
            
            if (!jsonString.startsWith("{")) continue;
            
            JsonObject jsonObject = Mossy.gson.fromJson(jsonString, JsonObject.class);
            String recipeType = jsonObject.get("type").getAsString();
            
            switch (recipeType) {
                case "minecraft:crafting_shapeless" -> parseCraftingShapeless(recipeId, jsonObject);
                case "minecraft:crafting_shaped" -> parseCraftingShaped(recipeId, jsonObject);
                case "minecraft:stonecutting" -> parseStoneCutting(recipeId, jsonObject);
                case "minecraft:smoking" -> parseFurnaceRecipe(recipeId, jsonObject, "smoking");
                case "minecraft:campfire_cooking" -> parseFurnaceRecipe(recipeId, jsonObject, "campfire_cooking");
                case "minecraft:smelting" -> parseFurnaceRecipe(recipeId, jsonObject, "smelting");
                case "minecraft:blasting" -> parseFurnaceRecipe(recipeId, jsonObject, "blasting");
                default -> {
                    if (Mossy.debug) Logger.warn("Incompatible recipe type (" + recipeType + ") for " + recipeId);
                }
            }
        }
        
        Logger.info("Recipes loaded successfully. (" + recipes.size() + " recipes)");
    }
    
    private static void parseCraftingShapeless(String recipeId, JsonObject jsonObject) {
        JsonElement group = jsonObject.get("group");
        JsonElement count = jsonObject.getAsJsonObject("result").get("count");
        String category = jsonObject.get("category").getAsString().toUpperCase(Locale.ENGLISH);
        String result = jsonObject.getAsJsonObject("result").get("item").getAsString();

        List<DeclareRecipesPacket.Ingredient> ingredients = RecipeUtils.getShapelessIngredients(jsonObject.getAsJsonArray("ingredients"));
        
        addRecipe(new ShapelessRecipe(
                recipeId,
                group == null ? "" : group.getAsString(), 
                RecipeCategory.Crafting.valueOf(category),
                ingredients, 
                ItemStack.of(Material.fromNamespaceId(NamespaceID.from(result)), count != null ? count.getAsInt() : 1)) {
            @Override
            public boolean shouldShow(@NotNull Player player) {
                return true;
            }
        });
    }

    private static void parseCraftingShaped(String recipeId, JsonObject jsonObject) {
        JsonElement group = jsonObject.get("group");
        JsonElement count = jsonObject.getAsJsonObject("result").get("count");
        String category = jsonObject.get("category").getAsString().toUpperCase(Locale.ENGLISH);
        String result = jsonObject.getAsJsonObject("result").get("item").getAsString();
        JsonElement showNotification = jsonObject.getAsJsonObject("show_notification");
        
        List<JsonElement> pattern = jsonObject.getAsJsonArray("pattern").asList();

        int with = pattern.get(0).getAsString().length();
        int height = pattern.size();
        
        StringBuilder materialStringBuilder = new StringBuilder();
        pattern.forEach(materialStringBuilder::append);
        String materialString = materialStringBuilder.toString().replace("\"", "");
        
        Map<Character, List<Material>> keys = RecipeUtils.getShapedKeys(jsonObject.getAsJsonObject("key"));
        List<DeclareRecipesPacket.Ingredient> ingredients = RecipeUtils.getShapedIngredients(keys, materialString);
        
        addRecipe(new ShapedRecipe(
                recipeId,
                with,
                height,
                group == null ? "" : group.getAsString(),
                RecipeCategory.Crafting.valueOf(category),
                ingredients,
                ItemStack.of(Material.fromNamespaceId(NamespaceID.from(result)), count != null ? count.getAsInt() : 1),
                showNotification == null ? true : showNotification.getAsBoolean()) {
            @Override
            public boolean shouldShow(@NotNull Player player) {
                return true;
            }
        });
    }
    
    private static void parseStoneCutting(String recipeId, JsonObject jsonObject) {
        JsonElement group = jsonObject.get("group");
        int count = jsonObject.get("count").getAsInt();
        String result = jsonObject.get("result").getAsString();
        String ingredient = jsonObject.getAsJsonObject("ingredient").get("item").getAsString();
        
        addRecipe(new StonecutterRecipe(
                recipeId,
                group == null ? "" : group.getAsString(),
                new DeclareRecipesPacket.Ingredient(Collections.singletonList(ItemStack.of(Material.fromNamespaceId(ingredient)))),
                ItemStack.of(Material.fromNamespaceId(result), count)
        ) {
            @Override
            public boolean shouldShow(@NotNull Player player) {
                return true;
            }
        });
    }
    
    private static void parseFurnaceRecipe(String recipeId, JsonObject jsonObject, String type) {
        JsonElement group = jsonObject.get("group");
        String category = jsonObject.get("category").getAsString().toUpperCase(Locale.ENGLISH);
        Material material = Material.fromNamespaceId(jsonObject.get("result").getAsString());
        float experience = jsonObject.get("experience").getAsFloat();
        int cookingtime = jsonObject.get("cookingtime").getAsInt();
        
        
        Recipe recipe = switch (type) {
            case "smoking" -> new SmokingRecipe(recipeId, group == null ? "" : group.getAsString(), RecipeCategory.Cooking.valueOf(category), ItemStack.of(material), experience, cookingtime) {
                @Override
                public boolean shouldShow(@NotNull Player player) {
                    return true;
                }
            };
            
            case "campfire_cooking" -> new CampfireCookingRecipe(recipeId, group == null ? "" : group.getAsString(), RecipeCategory.Cooking.valueOf(category), ItemStack.of(material), experience, cookingtime) {
                @Override
                public boolean shouldShow(@NotNull Player player) {
                    return true;
                }
            };

            case "smelting" -> new SmeltingRecipe(recipeId, group == null ? "" : group.getAsString(), RecipeCategory.Cooking.valueOf(category), ItemStack.of(material), experience, cookingtime) {
                @Override
                public boolean shouldShow(@NotNull Player player) {
                    return true;
                }
            };

            case "blasting" -> new BlastingRecipe(recipeId, group == null ? "" : group.getAsString(), RecipeCategory.Cooking.valueOf(category), ItemStack.of(material), experience, cookingtime) {
                @Override
                public boolean shouldShow(@NotNull Player player) {
                    return true;
                }
            };
            
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
        
        addRecipe(recipe);
    }
    
    private static void addRecipe(Recipe recipe) {
        MinecraftServer.getRecipeManager().addRecipe(recipe);
    }
}
