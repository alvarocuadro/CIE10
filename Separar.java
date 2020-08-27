public class Separar {
	
	public String separar(String args) {
        String s1 = args; 
       
        String[] parts = s1.split(" ");  //separa las palabras por espacio
        String query = "SELECT * FROM icd WHERE icd_nom like '%";
               
        for(int i=0;i<parts.length;i++) {
        
    if(i==parts.length-1) {//si es la última palabra, solo finaliza el script
    	query = query + parts[i] + "%'";
    }else {//si no es la última palabra, sigue agregando condiciones por cada palabra
        	
        	 
        	 query = query + parts[i] + "%' AND icd_nom like '%";
        	 
        	}
          }
        System.out.println(query);//retorna la query de SQL
        return query;
       
	}
	} 	