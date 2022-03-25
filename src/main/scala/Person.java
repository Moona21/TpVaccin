import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;

import java.util.Random;

public class Person {
    String lastName ;
    String firstName;
    String mid;
    String gender;
    String dateV;
    String vaccin;
    String adresse;
    String sideEffect;
    String siderCode;
    private Random rndm = new Random();
    private String[] sideEf = new String[]{"Injection site pain","fatigue","headache","Muscle pain ","chills ","Joint pain ","fever ",
            "Injection site swelling ","Nausea ","Malaise ","Lymphadenopathy "," Injection site tenderness " };
    private String[] sideC = new String[]{"C0151828","C0015672","C0018681","C0231528","C0085593","C0003862","C0015967",
            "C0151605","C0027497","C0231218","C0497156","C0863083"};

    public GenericRecord apply(Schema schema) {
        var builder = new GenericRecordBuilder(schema);
        builder
                .set("lastName", firstName)
                .set("firstName", lastName)
                .set("mid", mid)
                .set("gender", gender)
                .set("dateV", dateV)
                .set("vaccin", vaccin)
                .set("adresse", adresse)
                .set("sideEffect", sideEffect)
                .set("siderCode", siderCode);

        return builder.build();
    }

    public Person(String lastName,
                  String firstName,
                  String mid,
                  String gender,
                  String dateV,
                  String vaccin,
                  String adresse){
        this.lastName = lastName;
        this.firstName = firstName;
        this.mid = mid;
        this.gender = gender;
        this.dateV = dateV;
        this.vaccin = vaccin;
        this.adresse = adresse;
        var indice = rndm.nextInt(sideEf.length);
        this.sideEffect = sideEf[indice];
        this.siderCode = sideC[indice];

    }

    public  Person(){}

    public String getDateV() {
        return dateV;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMid() {
        return mid;
    }

    public String getSideEffect() {
        return sideEffect;
    }

    public String getVaccin() {
        return vaccin;
    }

    public String getSiderCode() {
        return siderCode;
    }

    public String getAdresse() {
        return adresse;
    }


    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDateV(String dateV) {
        this.dateV = dateV;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public void setVaccin(String vaccin) {
        this.vaccin = vaccin;
    }

    public void setSiderCode(String siderCode) {
        this.siderCode = siderCode;
    }

    @Override
    public String toString() {
        return "fr.uge.bd.Person{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", mid='" + mid + '\'' +
                ", gender='" + gender + '\'' +
                ", dateV='" + dateV + '\'' +
                ", vaccin='" + vaccin + '\'' +
                ", adresse='" + adresse + '\'' +
                ", sideEffect='" + sideEffect + '\'' +
                ", siderCode='" + siderCode + '\'' +
                '}';
    }
}
