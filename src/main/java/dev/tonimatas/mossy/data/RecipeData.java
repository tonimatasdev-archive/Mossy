package dev.tonimatas.mossy.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.tonimatas.mossy.Mossy;
import dev.tonimatas.mossy.launcher.Main;
import dev.tonimatas.mossy.logger.Logger;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.server.play.DeclareRecipesPacket;
import net.minestom.server.recipe.Recipe;
import net.minestom.server.recipe.RecipeCategory;
import net.minestom.server.recipe.ShapelessRecipe;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
            String recipeName = "minecraft:" + recipe.split("/")[recipe.split("/").length - 1].replace(".json", "");
            Scanner scanner = new Scanner(inputStream);
            
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.next());
            }
            
            scanner.close();
            String jsonString = stringBuilder.toString();
            
            if (jsonString.contains("tag")) {
                Logger.error("The recipe " + recipeName + " has tag. Unsupported at the moment.");
                continue;
            }
            
            if (!jsonString.startsWith("{")) continue;
            
            JsonObject jsonObject = Mossy.gson.fromJson(jsonString, JsonObject.class);
            String recipeType = jsonObject.get("type").getAsString();
            
            switch (recipeType) {
                case "minecraft:crafting_shapeless" -> parseCraftingShapeless(recipeName, jsonObject);
                default -> Logger.warn("Incompatible recipe type (" + recipeType + ") for " + recipeName);
            }
        }
        
        Logger.info("Recipes loaded successfully. (" + recipes.size() + ")");
    }
    
    private static void parseCraftingShapeless(String recipeName, JsonObject jsonObject) {
        JsonElement group = jsonObject.get("group");
        JsonElement count = jsonObject.getAsJsonObject("result").get("count");
        String category = jsonObject.get("category").getAsString().toUpperCase(Locale.ENGLISH);
        List<JsonElement> ingredients = jsonObject.getAsJsonArray("ingredients").asList();
        String result = jsonObject.getAsJsonObject("result").get("item").getAsString();
        
        addRecipe(new ShapelessRecipe(recipeName,
                group == null ? "" : group.getAsString(), RecipeCategory.Crafting.valueOf(category),
                getShapelessIngredients(ingredients), 
                ItemStack.of(Material.fromNamespaceId(NamespaceID.from(result)), 
                        count != null ? count.getAsInt() : 1)) {
            @Override
            public boolean shouldShow(@NotNull Player player) {
                return true;
            }
        });
    }
    
    private static List<DeclareRecipesPacket.Ingredient> getShapelessIngredients(List<JsonElement> jsonElements) {
       List<DeclareRecipesPacket.Ingredient> ingredients = new ArrayList<>();
        for (JsonElement jsonElement : jsonElements) {
            if (jsonElement.isJsonObject()) {
                ingredients.add(new DeclareRecipesPacket.Ingredient(List.of(ItemStack.of(Material.fromNamespaceId(jsonElement.getAsJsonObject().get("item").getAsString())))));
            }
            
            if (jsonElement.isJsonArray()) {
                List<ItemStack> itemStacks = new ArrayList<>();
                
                for (JsonElement jsonElement1 : jsonElement.getAsJsonArray().asList()) {
                    itemStacks.add(ItemStack.of(Material.fromNamespaceId(jsonElement1.getAsJsonObject().get("item").getAsString())));
                }

                ingredients.add(new DeclareRecipesPacket.Ingredient(itemStacks));
            }
        }
        
        return ingredients;
    }
    
    private static void addRecipe(Recipe recipe) {
        MinecraftServer.getRecipeManager().addRecipe(recipe);
    }
}
