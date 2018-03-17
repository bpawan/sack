package com.bpawan.dal.test.utils

import org.scalatest._
import org.scalatest.concurrent.ScalaFutures

trait CassandraSpec extends FlatSpec
  with Matchers
  with Inspectors
  with ScalaFutures
  with OptionValues
  with BeforeAndAfterAll
