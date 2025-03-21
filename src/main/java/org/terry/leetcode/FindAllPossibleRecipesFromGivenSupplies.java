package org.terry.leetcode;

import org.junit.jupiter.api.Assertions;

import java.util.*;
import java.util.stream.Stream;

/**
 * <a href="https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/">
 * 2115. Find All Possilbe Recipes from Given Supplies
 * </a>
 */
public class FindAllPossibleRecipesFromGivenSupplies {
    public static void main(String[] args) {
        new FindAllPossibleRecipesFromGivenSupplies().run();
    }

    void run() {
        List<String> recipes;
        List<List<String>> ingredients;
        List<String> supplies;

        // case 1
        recipes = List.of("bread");
        ingredients = List.of(List.of("yeast", "flour"));
        supplies = List.of("yeast", "flour", "corn");
        Assertions.assertEquals(List.of("bread"),
            findAllRecipes(recipes.toArray(new String[1]), ingredients, supplies.toArray(new String[1])));

        // case 2
        recipes = List.of("bread", "sandwich");
        ingredients = List.of(List.of("yeast", "flour"), List.of("bread", "meat"));
        supplies = List.of("yeast", "flour", "meat");
        Assertions.assertEquals(List.of("bread", "sandwich"),
            findAllRecipes(recipes.toArray(new String[1]), ingredients, supplies.toArray(new String[1])));

        // case 3
        recipes = List.of("bread", "sandwich", "burger");
        ingredients = List.of(List.of("yeast", "flour"), List.of("bread", "meat"),
            List.of("sandwich", "meat", "bread"));
        supplies = List.of("yeast", "flour", "meat");
        Assertions.assertEquals(List.of("bread", "sandwich", "burger"),
            findAllRecipes(recipes.toArray(new String[1]), ingredients, supplies.toArray(new String[1])));

        // case 4
        recipes = List.of("ju", "fzjnm", "x", "e", "zpmcz", "h", "q");
        ingredients = List.of(List.of("d"), List.of("hveml", "f", "cpivl"),
            List.of("cpivl", "zpmcz", "h", "e", "fzjnm", "ju"),
            List.of("cpivl", "hveml", "zpmcz", "ju", "h"), List.of("h", "fzjnm", "e", "q", "x"),
            List.of("d", "hveml", "cpivl", "q", "zpmcz", "ju", "e", "x"), List.of("f", "hveml", "cpivl"));
        supplies = List.of("f", "hveml", "cpivl", "d");
        Assertions.assertEquals(List.of("ju", "fzjnm", "q"),
            findAllRecipes(recipes.toArray(new String[1]), ingredients, supplies.toArray(new String[1])));
    }

    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> res = new ArrayList<>();
        Map<String, Boolean> canCook = new HashMap<>();
        Stream.of(supplies).forEach(s -> canCook.put(s, Boolean.TRUE));
        int cnt = 0;
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> recipeIngredients : ingredients) {
            map.put(recipes[cnt++], recipeIngredients);
        }

        for (String recipe : recipes) {
            if (dfs(map, map.get(recipe), canCook, recipe)) res.add(recipe);
        }
        return res;
    }

    boolean dfs(Map<String, List<String>> recipes, List<String> ingredients, Map<String, Boolean> canCook,
                String curRecipe) {
        if (canCook.containsKey(curRecipe)) {
            return canCook.get(curRecipe);
        }

        if (ingredients == null) return false;

        canCook.put(curRecipe, false);
        for (String ingredient : ingredients) {
            if (dfs(recipes, recipes.get(ingredient), canCook, ingredient)) continue;
            return false;
        }
        canCook.put(curRecipe, true);
        return true;
    }

}