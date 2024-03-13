package dev.tonimatas.mossy.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.server.play.DeclareRecipesPacket;

import java.util.*;

public class RecipeUtils {
    public static Map<Character, List<Material>> getShapedKeys(JsonObject keys) {
        Map<Character, List<Material>> keyMap = new HashMap<>();
        
        for (Map.Entry<String, JsonElement> key : keys.entrySet()) {
            List<Material> materials = new ArrayList<>();
            
            if (key.getValue().isJsonObject()) {
                String materialId = key.getValue().getAsJsonObject().get("item").getAsString();
                materials.add(Material.fromNamespaceId(materialId));
            } else {
                for (JsonElement item : key.getValue().getAsJsonArray()) {
                    String materialId = item.getAsJsonObject().get("item").getAsString();
                    materials.add(Material.fromNamespaceId(materialId));
                }
            }

            keyMap.put(key.getKey().charAt(0), materials);
        }
        
        return keyMap;
    }

    public static List<DeclareRecipesPacket.Ingredient> getShapedIngredients(Map<Character, List<Material>> keys, String pattern) {
        List<DeclareRecipesPacket.Ingredient> ingredients = new ArrayList<>();
        
        for (char character : pattern.toCharArray()) {
            List<ItemStack> itemStacks = new ArrayList<>();
            
            if (character == ' ') {
                itemStacks.add(ItemStack.AIR);
            } else {
                for (Material material : keys.get(character)) {
                    itemStacks.add(ItemStack.of(material));
                }
            }
            
            ingredients.add(new DeclareRecipesPacket.Ingredient(itemStacks));
        }
        
        return ingredients;
    }

    public static List<DeclareRecipesPacket.Ingredient> getShapelessIngredients(JsonArray jsonArray) {
        List<DeclareRecipesPacket.Ingredient> ingredients = new ArrayList<>();
        
        for (JsonElement jsonElement : jsonArray) {
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
}
