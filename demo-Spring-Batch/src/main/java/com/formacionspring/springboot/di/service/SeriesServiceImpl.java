package com.formacionspring.springboot.di.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionspring.springboot.di.entity.Series;

@Service
public class SeriesServiceImpl implements ISeriesService{

private final String FETCH_SQL="SELECT * FROM SERIES WHERE estreno=(SELECT MAX(ESTRENO) FROM SERIES) AND TEMPORADAS=(SELECT MAX(TEMPORADAS) FROM SERIES) AND PUNTUACION=110";	


@Autowired
private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Series> finAll() {
		// TODO Auto-generated method stub
		return namedParameterJdbcTemplate.query(FETCH_SQL, new SeriesMapper());
	}
	
	@SuppressWarnings("rawtypes")
	class SeriesMapper implements RowMapper {

		@Override
		public Series mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			
			Series serie=new Series();
			
			serie.setId(rs.getLong("id"));
			serie.setTitulo(rs.getString("titulo"));
			serie.setClasificacion(rs.getString("clasificacion"));
			serie.setPuntuacion(rs.getInt("puntuacion"));
			serie.setEstreno(rs.getInt("estreno"));
			serie.setFecha(rs.getDate("fecha"));
			serie.setTemporadas(rs.getInt("temporadas"));
			return serie;
		}
	
	}
}


