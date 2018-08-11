package com.bpawan.dal.entity

case class ProductCategory(id: Long, name: String, description: String)

case class ProductType(id: Long, name: String, description: String)

case class Product(id: Long, name: String, description: String, category: ProductCategory, productType: ProductType, price: Long)

case class Inventory(id: Long, product: Product, amount: Int)


// add new product
// edit/delete product
// list products
// list products for provided inventory