package org.example1

// 1. กำหนด data class สำหรับเก็บข้อมูลสินค้า
data class Product(val name: String, val price: Double, val category: String)

fun main() {
    // 2. สร้างรายการสินค้าตัวอย่าง (List<Product>)
    // สินค้า name = "Laptop", price = 35000.0, category = "Electronics"
    // สินค้า name = "Smartphone", price = 25000.0, category = "Electronics"
    // สินค้า name = "T-shirt", price = 450.0, category = "Apparel"
    // สินค้า name = "Monitor", price = 7500.0, category = "Electronics"
    // สินค้า name = "Keyboard", price = 499.0, category = "Electronics" // ราคาไม่เกิน 500
    // สินค้า name = "Jeans", price = 1200.0, category = "Apparel"
    // สินค้า name = "Headphones", price = 1800.0, category = "Electronics" // ตรงตามเงื่อนไข
    val products = listOf(
        Product(name = "Laptop", price = 35000.0, category = "Electronics"),
        Product(name = "Smartphone", price = 25000.0, category = "Electronics"),
        Product(name = "T-shirt", price = 450.0, category = "Apparel"),
        Product(name = "Monitor", price = 7500.0, category = "Electronics"),
        Product(name = "Keyboard", price = 499.0, category = "Electronics"),
        Product(name = "Jeans", price = 1200.0, category = "Apparel"),
        Product(name = "Headphones", price = 1800.0, category = "Electronics")
    )

    println("รายการสินค้าทั้งหมด:")
    products.forEach { println(it) }
    println("--------------------------------------------------")

    // --- โจทย์: จงหาผลรวมราคาสินค้าทั้งหมดในหมวด 'Electronics' ที่มีราคามากกว่า 500 บาท ---

    // 3. วิธีที่ 1: การใช้ Chaining กับ List โดยตรง
    // กรองสินค้าหมวด Electronics
    // กรองสินค้าที่ราคามากกว่า 500
    // ดึงเฉพาะราคาออกมาเป็น List<Double>
    // หาผลรวมของราคา
    val totalElecPriceOver500 = products
        .filter { it.price > 500 }
        .filter { it.category == "Electronics" }
        .map { it.price }
        .sum()

    println("วิธีที่ 1: ใช้ Chaining กับ List")
    println("ผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท: $totalElecPriceOver500 บาท")
    println("--------------------------------------------------")


    // 4. (ขั้นสูง) วิธีที่ 2: การใช้ .asSequence() เพื่อเพิ่มประสิทธิภาพ
    // แปลง List เป็น Sequence ก่อนเริ่มประมวลผล
    val totalElecPriceOver500Sequence =  products.asSequence()
        .filter { it.price > 500 }
        .filter { it.category == "Electronics" }
        .map { it.price }
        .sum()

    println("วิธีที่ 2: ใช้ .asSequence() (ขั้นสูง)")
    println("ผลรวมราคาสินค้า Electronics ที่ราคา > 500 บาท: $totalElecPriceOver500Sequence บาท")
    println("--------------------------------------------------")

    val morethan10000 = products.asSequence()
        .filter { it.price > 10000 }

    val lowthan1000 = products.asSequence()
        .filter { it.price < 1000 }

    val between1000To9999 = products.asSequence()
        .filter { it.price < 10000 }
        .filter { it.price > 999 }

    println("กลุ่มสินค้าตามราคา:")

    println("10,000 บาทขึ้นไป: ")
    morethan10000.forEach { println( "- "+it.name+" ("+it.price+" บาท)" ) }

    println("ไม่เกิน 1,000 บาท: ")
    lowthan1000.forEach { println( "- "+it.name+" ("+it.price+" บาท)" ) }

    println("1,000 - 9,999 บาท: ")
    between1000To9999.forEach { println( "- "+it.name+" ("+it.price+" บาท)" ) }

    println("--------------------------------------------------")

    data class Product(val name: String, val category: String, val price: Double)

}
fun forTestTotalElecPriceOver500(products: List<Product>): Double {
    return products
        .filter { it.price > 500 }
        .filter { it.category == "Electronics" }
        .sumOf { it.price }
}

fun forTestCountElecPriceOver500(products: List<Product>): Double {
    val count = products.count { it.price > 500 && it.category == "Electronics" }
    return count.toDouble()
}