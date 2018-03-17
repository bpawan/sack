package com.bpawan.api.serializer

import java.util.UUID

import com.bpawan.dal.entity.Song
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, JsString, JsValue, JsonFormat, RootJsonFormat, deserializationError}

trait JsonSupport extends SprayJsonSupport {

  import DefaultJsonProtocol._

  implicit object UuidJsonFormat extends JsonFormat[UUID] {
    def write(x: UUID) = JsString(x toString())

    def read(value: JsValue): UUID = value match {
      case JsString(x) => UUID.fromString(x)
      case x => deserializationError("Expected UUID as JsString, but got " + x)
    }
  }

  implicit val songFormat: RootJsonFormat[Song] = jsonFormat4(Song)
}
