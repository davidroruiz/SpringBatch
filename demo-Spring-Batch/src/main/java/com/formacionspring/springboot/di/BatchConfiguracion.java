package com.formacionspring.springboot.di;




import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.formacionspring.springboot.di.entity.Series;
import com.formacionspring.springboot.di.listener.JobListener;
import com.formacionspring.springboot.di.processor.MiProcesso;

@Configuration
@EnableBatchProcessing
public class BatchConfiguracion {
   
	
	@Autowired
	public JobBuilderFactory jobBuilderfactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource datasource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Bean
	public FlatFileItemReader<Series> reader(){
		return new FlatFileItemReaderBuilder<Series>()
				.name("seriesItemReader")
				.resource(new FileSystemResource("src/main/resources/Series.csv"))
				.delimited()
				.delimiter(";")
				.names(new String [] {"titulo","clasificacion","nivel_clasificacion","puntuacion","estreno","temporadas"})
				.targetType(Series.class)

				.build();
				
		
	}
	

	@Bean
	public MiProcesso processor() {
		return new MiProcesso();
	}
	
	
	
	@Bean
    public ItemWriter<Series> writer() {
      
        return new ItemWriter<Series>() {

			@Override
			public void write(List<? extends Series> items) throws Exception {
				// TODO Auto-generated method stub
				
				for (Series series : items) {
					if(series.getTitulo()!=null) {
						jdbcTemplate.update("INSERT INTO series(titulo,clasificacion,nivel_clasificacion,puntuacion,estreno,temporadas,fecha) VALUES (?,?,?,?,?,?,?)",
								series.getTitulo(),series.getClasificacion(),series.getNivel_clasificacion(),
								series.getPuntuacion(),series.getEstreno(),series.getTemporadas(),series.getFecha()
								
								
								); 
				}
					
				}
			}
        	
        };
       
    }
	

	
	@Bean
    public Job importSerieJob(JobListener listener) {
        return jobBuilderfactory.get("importSerieJob").incrementer(new RunIdIncrementer()).listener(listener)
                .flow(step1()).end().build();

 
    }
    
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Series, Series>chunk(50)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                .skipLimit(10000)
                .skip(RuntimeException.class)
                .build();
    }
	
	
	
	

}
