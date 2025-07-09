import com.example.Ingredients
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

import com.example.RecipesRepository
import com.example.IngredientsRepository
import com.example.RecipeIngredientRepository
import com.example.RecipesRequest
import com.example.IngredientsRequest
import com.example.RecipeIngredient
import com.example.Recipes

class WorkshopTest5 {

    @Test
    fun `test add new recipe`() {
        val request = RecipesRequest(name = "ข้าวผัดหมู", instructions = "1. ผัดข้าวกับหมู2. ปรุงรสและเสิร์ฟ")
        val recipe = RecipesRepository.add(request)

        assertNotNull(recipe)
        assertEquals("ข้าวผัดหมู", recipe.name)
    }

    @Test
    fun `test get recipe by id`() {
        val recipe = RecipesRepository.getById(1)
        assertNotNull(recipe)
        assertEquals("ต้มยำกุ้ง", recipe.name)
    }

    @Test
    fun `test add new ingredient`() {
        val request = IngredientsRequest(name = "ซีอิ๊วขาว", quantity = 1, unit = "ช้อนโต๊ะ")
        val ingredient = IngredientsRepository.add(request)

        assertNotNull(ingredient)
        assertEquals("ซีอิ๊วขาว", ingredient.name)
    }

    @Test
    fun `test get ingredient by id`() {
        val ingredient = IngredientsRepository.getById(1)
        assertNotNull(ingredient)
        assertEquals("น้ำเปล่า", ingredient.name)
    }

    @Test
    fun `test add and find recipe ingredient pair`() {
        RecipeIngredientRepository.clear()

        val newPair = RecipeIngredient(recipeId = 2, ingredientId = 1, quantity = 200, unit = "ml")
        val added = RecipeIngredientRepository.add(newPair)
        assertTrue(added)

        val all = RecipeIngredientRepository.getAll()
        assertTrue(all.any { it.recipeId == 2 && it.ingredientId == 1 })
    }

    @Test
    fun `test get all recipes returns at least 3`() {
        val allRecipes = RecipesRepository.getAll()
        assertTrue(allRecipes.size >= 3)
    }

    @Test
    fun `test get all ingredients returns at least 3`() {
        val allIngredients = IngredientsRepository.getAll()
        assertTrue(allIngredients.size >= 3)
    }

    @Test
    fun `test search recipe`() {

    }
    @Test
    fun `test update recipe`() {
        val original = RecipesRepository.add(RecipesRequest("แกงเขียวหวาน", "1. เคี่ยวกะทิ\n2. ใส่พริกแกงและเนื้อ"))
        val updated = Recipes(original.id, "แกงเขียวหวานไก่", "1. เคี่ยวกะทิ\n2. ใส่พริกแกง\n3. ใส่ไก่")

        val success = RecipesRepository.update(original.id, updated)
        assertTrue(success)

        val result = RecipesRepository.getById(original.id)
        assertEquals("แกงเขียวหวานไก่", result?.name)
    }

    @Test
    fun `test delete recipe`() {
        val recipe = RecipesRepository.add(RecipesRequest("ยำวุ้นเส้น", "1. ลวกวุ้นเส้น\n2. ปรุงรส"))
        val deleted = RecipesRepository.delete(recipe.id)
        assertTrue(deleted)

        val result = RecipesRepository.getById(recipe.id)
        assertEquals(null, result)
    }
    @Test
    fun `test search recipe by ingredient`() {
        val results = RecipesRepository.search("น้ำปลา")
        assertTrue(results.isNotEmpty())

        val found = results.any { (recipe, ingredients) ->
            recipe.name.contains("ต้มยำกุ้ง") &&
                    ingredients.any { it.unit == "ช้อนโต๊ะ" }
        }

        assertTrue(found)
    }
    @Test
    fun `test update ingredient`() {
        val ingredient = IngredientsRepository.add(IngredientsRequest("กระเทียม", 3, "กลีบ"))
        val updated = Ingredients(ingredient.id, "กระเทียมสับ", 2, "ช้อนชา")

        val success = IngredientsRepository.update(ingredient.id, updated)
        assertTrue(success)

        val result = IngredientsRepository.getById(ingredient.id)
        assertEquals("กระเทียมสับ", result?.name)
    }

    @Test
    fun `test delete recipe ingredient pair`() {
        val pair = RecipeIngredient(1, 3, 2, "ช้อนโต๊ะ")
        RecipeIngredientRepository.add(pair)

        val deleted = RecipeIngredientRepository.delete(1, 3)
        assertTrue(deleted)

        val found = RecipeIngredientRepository.getByRecipeAndIngredient(1, 3)
        assertEquals(null, found)
    }
    @Test
    fun `test update recipe ingredient`() {
        val original = RecipeIngredient(recipeId = 2, ingredientId = 1, quantity = 200, unit = "ml")
        RecipeIngredientRepository.add(original)

        val updated = RecipeIngredient(recipeId = 2, ingredientId = 1, quantity = 250, unit = "ml")
        val success = RecipeIngredientRepository.update(2, 1, updated)
        assertTrue(success)

        val result = RecipeIngredientRepository.getByRecipeAndIngredient(2, 1)
        assertEquals(250, result?.quantity)
    }

    //ลองทดสอบกรณีไม่สำเร็จ
 /*   @Test
    fun `test cannot add duplicate recipe-ingredient pair`() {
        val pair = RecipeIngredient(1, 3, 2, "ช้อนโต๊ะ")
        val firstAdd = RecipeIngredientRepository.add(pair)
        val secondAdd = RecipeIngredientRepository.add(pair)

        assertTrue(firstAdd)
        assertTrue(!secondAdd) // ควรไม่สำเร็จ
    }
*/
} 
