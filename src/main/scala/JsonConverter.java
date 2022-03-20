import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonConverter  {
    private ObjectMapper objt = new ObjectMapper();
    public  JsonConverter(){

    }
    public String generer(Person ps){
        //transformer person en json
        try{
            objt.setVisibility(JsonMethod.FIELD, JsonAutoDetect.Visibility.ANY);
            return objt.writeValueAsString(ps);
        }catch (JsonGenerationException | JsonMappingException e){
            return "ExceptionGeneration / ExceptionMapping "+ e;
        } catch (IOException e){
            return "IO  : "+ e;
        }
    }
}
