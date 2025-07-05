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
} 
