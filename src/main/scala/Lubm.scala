import com.github.javafaker.Faker
import org.apache.jena.ontology.OntModelSpec
import org.apache.jena.rdf.model.ModelFactory

import java.io.{File, FileOutputStream}
import scala.util.Random

object Lubm extends App {
  val mid = "http://www.w3.org/1999/02/22-rdf-syntax-ns#id"
  val lastN = "http://www.w3.org/1999/02/22-rdf-syntax-ns#lastN"
  val firstN = "http://www.w3.org/1999/02/22-rdf-syntax-ns#FirstN"
  val gender ="http://www.w3.org/1999/02/22-rdf-syntax-ns#gender"
  val dateV = "http://www.w3.org/1999/02/22-rdf-syntax-ns#dateV"
  val vaccin = "http://www.w3.org/1999/02/22-rdf-syntax-ns#vaccin"
  val adresse = "http://www.w3.org/1999/02/22-rdf-syntax-ns#adresse"


  val FILE1 = "file:////C:/Users/igm/Desktop/ExamVaccin/lubm1.ttl"
  val ml = ModelFactory.createDefaultModel();
  ml.read(FILE1,"TTL")
 // ml.listStatements().toList.forEach(println(_))

  val idProperty = ml.createProperty(mid)
  val lastNPorperty = ml.createProperty(lastN)
  val firstNProperty =ml.createProperty(firstN)
  val genderProperty = ml.createProperty(gender)
  val dateVProperty =ml.createProperty(dateV)
  val vaccinProperty =ml.createProperty(vaccin)
  val adressProperty =ml.createProperty(adresse)
  //liste des vaccins
  val vaccinList = List("BioNTech","Pfizer","Johnson & Johnson","Moderna","Oxford","AstraZeneca","Novavax")

  val faker = new Faker()
  val rnd = new Random()




  val FILE = "file:////C:/Users/igm/Desktop/ExamVaccin/univ-bench.owl"
  val md = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF)
  md.read(FILE)
  //md.listStatements().toList.forEach(println(_))

  val person = md.getOntClass("http://swat.cse.lehigh.edu/onto/univ-bench.owl#Person") // defini sur le fichier
  //person.listSubClasses().toList.forEach(println(_))
  person.listSubClasses().forEach(p=>{
    //prendre tt les instances des sousclasses ensuite les enrichis
       val instance = ml.listResourcesWithProperty(ml.getProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"),ml.getProperty(p.toString))
      instance.forEach(i=>{
        //ajouter un id , first name , lastname , le sexe , adresse , le nom du vaccin , et la date de vaccination
        ml.add(ml.createStatement(i,idProperty,ml.createLiteral(faker.number().randomDigit().toString))) //enrichir pour l id
        ml.add(ml.createStatement(i,firstNProperty,ml.createLiteral(faker.name().firstName())))
        ml.add(ml.createStatement(i,lastNPorperty,ml.createLiteral(faker.name().lastName())))
        ml.add(ml.createStatement(i,genderProperty,ml.createLiteral(faker.demographic().sex())))
        ml.add(ml.createStatement(i,adressProperty,ml.createLiteral(faker.address().toString)))
        if (rnd.nextInt(2)==0){
          ml.add(ml.createStatement(i,dateVProperty,ml.createLiteral(faker.date().birthday(0,2).toString)))
          ml.add(ml.createStatement(i,vaccinProperty,ml.createLiteral(vaccinList(rnd.nextInt(vaccinList.length)))))
        }

      })

  })
   // ml.listResourcesWithProperty(vaccinProperty).forEach(println(_))
  // j'cris mon modele dans un autre fichier de sortie
  val fichierS= new File("C:/Users/igm/Desktop/ExamVaccin/LubmOutput.ttl")
  val fichierSStream = new FileOutputStream(fichierS)
  ml.write(fichierSStream)

  //creer une classe personne et a classe aura lees proprietés enrichis dans le fichier lumboutput
  // lire le fichier lubmoutput
  // recuperer les personnes vacciné chaque pero vaccine retourne un objet de type personne
  // converti chaque objet en json
  // passer le json au producteur pour le produire
  //creer un consumer il  va consommer le json
  // utiliser le format avro
  // utiliser l api kafka stream








}
