package com.bpawan.dal.database

import com.bpawan.dal.model.ProductModel
import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.dsl._

class ProductDatabase (override val connector: CassandraConnection) extends Database[ProductDatabase](connector) {

  object ProductModel extends ProductModel with connector.Connector

}
