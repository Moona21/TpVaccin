import org.apache.avro.reflect.AvroSchema

import java.io.FileInputStream
import com.twitter.bijection.Injection
import com.twitter.bijection.avro.GenericAvroCodecs
import org.apache.avro.Schema
import org.apache.avro.generic.{GenericRecord, GenericRecordBuilder}

object Test extends App {

  val prsn = new Person("serrar","mouna","5","female","2106","pfizer","93360")
  //println(prsn)
 // val schemaPath = getClass.getResource("C:/Users/igm/Desktop/ExamVaccin/PersonAvroSchema.avsc")
  //println(schemaPath)
  val inptstrm= new FileInputStream("C:/Users/igm/Desktop/ExamVaccin/PersonAvroSchema.avsc")
  val prsr = new Schema.Parser()
  val path = prsr.parse(inptstrm)
  println(path)

  val recordInjection : Injection[GenericRecord, Array[Byte]]  = GenericAvroCodecs.toBinary(path)
 // recordInjection.apply(Torecord(prsn))

  def Torecord(person: Person): GenericRecord ={
    val builder = new GenericRecordBuilder(path)
    builder

      .set("lastName", person.lastName)
      .set("firstName", person.firstName)
      .set("mid", person.mid)
      .set("gender", person.gender)
      .set("dateV", person.dateV)
      .set("vaccin", person.vaccin)
      .set("adresse", person.adresse)
      .set("sideEffect", person.sideEffect)
      .set("siderCode", person.siderCode)

    builder.build()

  }

   val res = recordInjection.apply(Torecord(prsn))
  println(res)




}
