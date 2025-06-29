import kotlin.test.Test
import kotlin.test.assertEquals

import org.example.celsiusToFahrenheit
import org.example.kilometersToMiles

import org.example1.forTestTotalElecPriceOver500
import org.example1.forTestCountElecPriceOver500
import org.example1.Product

class WorkshopTest {

    // --- Tests for Workshop #1: Unit Converter ---

    // celsius input: 20.0
    // expected output: 68.0
    @Test
    fun `test celsiusToFahrenheit with positive value`() {
        // Arrange: ตั้งค่า input และผลลัพธ์ที่คาดหวัง
        val celsiusInput = 20.0
        val expectedFahrenheit = 68.0

        // Act: เรียกใช้ฟังก์ชันที่ต้องการทดสอบ
        val actualFahrenheit = celsiusToFahrenheit(celsiusInput)

        // Assert: ตรวจสอบว่าผลลัพธ์ที่ได้ตรงกับที่คาดหวัง
        assertEquals(expectedFahrenheit, actualFahrenheit, 0.001, "20°C should be 68°F")
    }

    // celsius input: 0.0
    // expected output: 32.0
    @Test
    fun `test celsiusToFahrenheit with zero`() {
        val input = 0.0
        val expected = 32.0
        val actual = celsiusToFahrenheit(input)
        assertEquals(expected, actual, 0.001, "0°C should be 32°F")
    }

    // celsius input: -10.0
    // expected output: 14.0
    @Test
    fun `test celsiusToFahrenheit with negative value`() {
        val input = -10.0
        val expected = 14.0
        val actual = celsiusToFahrenheit(input)
        assertEquals(expected, actual, 0.001, "-10°C should be 14°F")
    }

    // test for kilometersToMiles function
    // kilometers input: 1.0
    // expected output: 0.621371
    @Test
    fun `test kilometersToMiles with one kilometer`() {
        val input = 1.0
        val expected = 0.621371
        val actual = kilometersToMiles(input)
        assertEquals(expected, actual, 0.001, "1 km should be 0.621371 miles")
    }

    // --- Tests for Workshop #1: Unit Converter End ---

    // --- Tests for Workshop #2: Data Analysis Pipeline ---
    // ทำการแก้ไขไฟล์ Workshop2.kt ให้มีฟังก์ชันที่ต้องการทดสอบ
    // เช่น ฟังก์ชันที่คำนวณผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
    // ในที่นี้จะสมมุติว่ามีฟังก์ชันชื่อ calculateTotalElectronicsPriceOver500 ที่รับ List<Product> และคืนค่า Double
    // จงเขียน test cases สำหรับฟังก์ชันนี้ โดยตรวจสอบผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท
    // 🚨
    @Test
    fun `test total Electronics over 500`() {
        val products = listOf(
            Product(name = "Laptop", price = 35000.0, category = "Electronics"),
            Product(name = "Smartphone", price = 25000.0, category = "Electronics"),
            Product(name = "T-shirt", price = 450.0, category = "Apparel"),
            Product(name = "Monitor", price = 7500.0, category = "Electronics"),
            Product(name = "Keyboard", price = 499.0, category = "Electronics"),
            Product(name = "Jeans", price = 1200.0, category = "Apparel"),
            Product(name = "Headphones", price = 1800.0, category = "Electronics")
        )

        val expected = 69300.0
        val actual = forTestTotalElecPriceOver500(products)

        assertEquals(expected, actual, 0.001,"จะได้ 69300 บาท")
    }


    // จงเขียน test cases เช็คจำนวนสินค้าที่อยู่ในหมวด 'Electronics' และมีราคามากกว่า 500 บาท
    // 🚨
    @Test
    fun `test count Electronics over 500`() {
        val products = listOf(
            Product(name = "Laptop", price = 35000.0, category = "Electronics"),
            Product(name = "Smartphone", price = 25000.0, category = "Electronics"),
            Product(name = "T-shirt", price = 450.0, category = "Apparel"),
            Product(name = "Monitor", price = 7500.0, category = "Electronics"),
            Product(name = "Keyboard", price = 499.0, category = "Electronics"),
            Product(name = "Jeans", price = 1200.0, category = "Apparel"),
            Product(name = "Headphones", price = 1800.0, category = "Electronics")
        )

        val expected = 4.0
        val actual = forTestCountElecPriceOver500(products)

        assertEquals(expected, actual, 0.001,"ควรมีแค่4")
    }

    // --- Tests for Workshop #2: Data Analysis Pipeline End ---
}