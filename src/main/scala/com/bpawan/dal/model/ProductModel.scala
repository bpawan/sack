package com.bpawan.dal.model

import com.bpawan.dal.entity.{ProductCategory, ProductType}
import com.outworkers.phantom.Table
import com.outworkers.phantom.keys.PartitionKey
import com.outworkers.phantom.dsl._

import scala.concurrent.Future

abstract class ProductModel extends Table[ProductModel, Product] {
  override def tableName = "products"

  object id extends LongColumn with PartitionKey

  object name extends StringColumn

  object description extends StringColumn

  object category extends CustomColumn

  object productType extends CustomColumn

  def getProductByCategory(productCategory: String): Future[ResultSet] = {
    select
      .where(_.category.name eqs category)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future
  }

  def getProductByType(productType: String): Future[ResultSet] = {
    select
      .where(_.productType.name eqs productType)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future
  }

  def getProductByName(name: String): Future[ResultSet] = {
    select
      .where(_.name eqs name)
      .consistencyLevel_=(ConsistencyLevel.ONE)
      .future
  }
}
