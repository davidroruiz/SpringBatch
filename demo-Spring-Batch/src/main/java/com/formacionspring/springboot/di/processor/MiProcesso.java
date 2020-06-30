package com.formacionspring.springboot.di.processor;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.formacionspring.springboot.di.entity.Series;

/* El processor es donde hacemos modificaciones a la informacion que esta procesando nuestro batch */
public class MiProcesso implements ItemProcessor<Series, Series>{

	
	private static final Logger LOG=LoggerFactory.getLogger(MiProcesso.class);
	private static Map<String,Series> duplicados= new HashMap<String,Series>();


	@Override
	public Series process(Series item) throws Exception {
		// TODO Auto-generated method stub
		 Long serieElementoId=item.getId();
		 String titulo=item.getTitulo().toUpperCase();

		 if(!duplicados.containsKey(titulo)) {
			 String   clasificion=item.getClasificacion();
			 
			 switch (item.getClasificacion()) {
			case "G":
				 clasificion=item.getClasificacion().replaceAll("G", "Todos los públicos");
				break;

			case "PG":
				clasificion=item.getClasificacion().replaceAll("PG", "Todos los públicos");
				break;
				
			case "TV-Y7":
				clasificion=item.getClasificacion().replaceAll("TV-Y7", "Mayores de 7 años");
				break;
				
			case "TV-Y":
				clasificion=item.getClasificacion().replaceAll("TV-Y", "Todos los públicos");
				break;
				
			case "TV-Y7-FV":
				clasificion=item.getClasificacion().replaceAll("TV-Y7-FV", "Mayores de 7 años. Fantasía violenta.");
				break;
				
			case "PG-13":
				clasificion=item.getClasificacion().replaceAll("PG-13", "Mayores de 13 años");
				break;
				
				
			case "TV-G":
				clasificion=item.getClasificacion().replaceAll("TV-G", "Todos los públicos");
				break;
				
				
			case "R":
				clasificion=item.getClasificacion().replaceAll("R", "Lenguaje obsceno y violencia.");
				break;
				
			case "TV-14":
				clasificion=item.getClasificacion().replaceAll("TV-14", "Mayores de 14 años");
				break;
				
			case "TV-PG":
				clasificion=item.getClasificacion().replaceAll("TV-PG", "Bajo supervisión parental");
				break;
				
			case "TV-MA":
				clasificion=item.getClasificacion().replaceAll("TV-MA", "Mayores de 17 años");
				break;
				
				
			case "NR":
				clasificion=item.getClasificacion().replaceAll("NR", "Sin calificar");
				break;
				
				
			case "UR":
				clasificion=item.getClasificacion().replaceAll("UR", "Sin calificar. Sólo adultos.");
				break;

				
			default:
				break;
			}
			 
			 String nivel_clasificacion=item.getNivel_clasificacion().toUpperCase();
			 int puntuacion=item.getPuntuacion();
			 int estreno=item.getEstreno();
			 int temporadas=item.getTemporadas();
			Date fecha=new Date();
			DateFormat  dateFormat  = new SimpleDateFormat("dd-M-yyyy hh:mm"); 
			dateFormat.format(fecha);
			 
			 item=new Series(serieElementoId,titulo,clasificion,nivel_clasificacion,puntuacion,estreno,temporadas,fecha);
			LOG.info("Convirtiendo ("+item+") a ("+item+")");
			duplicados.put(titulo, item);
		 }else {
			 item=new Series();
		 }
	
		
		 return item;
	}
	
	

}
