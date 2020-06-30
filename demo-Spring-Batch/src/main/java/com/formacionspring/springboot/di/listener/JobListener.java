package com.formacionspring.springboot.di.listener;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.formacionspring.springboot.di.entity.Series;



/* Nos permitira saber el estado de ejecucion del Job*/

@Component
public class JobListener extends JobExecutionListenerSupport{

	private static final Logger LOG=LoggerFactory.getLogger(JobListener.class);

	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JobListener(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
		public void afterJob(JobExecution jobExecution) {
			// TODO Auto-generated method stub
			if(jobExecution.getStatus()==BatchStatus.COMPLETED) {
				LOG.info("Finalizo el JOB!! Verifica los resultados:");
				
				jdbcTemplate.query("SELECT id,titulo,clasificacion,nivel_clasificacion,puntuacion,estreno,temporadas,fecha FROM series", 
						(rs,row)->{return new Series(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getDate(8));})
				/*.query("DELETE nivel_clasificacionfecha FROM series", 
						(rs,row)->new Series(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getInt(7),rs.getDate(8)))*/
				.forEach(series->LOG.info("Registro <"+series+" >"));
				

				jdbcTemplate.execute("ALTER TABLE series DROP COLUMN nivel_clasificacion");
				

			}

		}
	
	
	

	
			
			

		
	
}
