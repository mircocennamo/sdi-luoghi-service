package it.interno.luoghi.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.interno.luoghi.entity.Luogo;

public interface LuogoFattoRepository extends CrudRepository<Luogo, Integer> , JpaSpecificationExecutor<Luogo> {
	@Query(value="SELECT \r\n"
			+ "CODICELUOGO,\r\n"
			+ "DATAINIZIOVALIDITA,\r\n"
			+ "INLUOGO,\r\n"
			+ "TRIM(DESCRIZIONELUOGO) AS DESCRIZIONELUOGO,\r\n"
			+ "DATAFINEVALIDITA,\r\n"
			+ "CODICEREGIONE,\r\n "
			+ "CODICEPROVINCIA,\r\n"
			+ "CODICECOMUNE,\r\n"
			+ "CODICECATASTALE,\r\n"
			+ "SIGLAPROVINCIA,\r\n"
			+ "TSINSERIMENTO,\r\n"
			+ "TSCANCELLAZIONE,\r\n"
			+ "CODICELUOGOAREA,\r\n"
			+ "CODICELUOGOZONA,\r\n"
			+ "CODICELUOGORIFERIMENTO,\r\n"
			+ "SIGLALUOGOASF\r\n"
			+ "FROM DBLUOGHI.TLUOGO \r\n"
			+ "WHERE INLUOGO = :inputLuogo \r\n"
			+ "AND :inputDataPf BETWEEN DATAINIZIOVALIDITA AND NVL(DATAFINEVALIDITA , SYSDATE)\r\n"
			+ "AND TSCANCELLAZIONE IS NULL"
			+ "AND TRIM(DESCRIZIONELUOGO) LIKE '%'||:inputDescrizione||'%'", nativeQuery=true)
	public List<Luogo> getLuogoFatto(@Param(value = "inputLuogo")  String inputLuogo,
									 @Param(value = "inputDataPf") LocalDate inputDataPf,
									 @Param(value = "inputDescrizione") String inputDescrizione);
	
	
	@Query(value="SELECT \r\n"
			+ "CODICELUOGO,\r\n"
			+ "DATAINIZIOVALIDITA,\r\n"
			+ "INLUOGO,\r\n"
			+ "TRIM(DESCRIZIONELUOGO) AS DESCRIZIONELUOGO,\r\n"
			+ "DATAFINEVALIDITA,\r\n"
			+ "CODICEREGIONE,\r\n "
			+ "CODICEPROVINCIA,\r\n"
			+ "CODICECOMUNE,\r\n"
			+ "CODICECATASTALE,\r\n"
			+ "SIGLAPROVINCIA,\r\n"
			+ "TSINSERIMENTO,\r\n"
			+ "TSCANCELLAZIONE,\r\n"
			+ "CODICELUOGOAREA,\r\n"
			+ "CODICELUOGOZONA,\r\n"
			+ "CODICELUOGORIFERIMENTO,\r\n"
			+ "SIGLALUOGOASF\r\n"
			+ "FROM DBLUOGHI.TLUOGO \r\n"
			+ "WHERE INLUOGO = :inputLuogo \r\n"
			+ "AND :inputDataPf BETWEEN DATAINIZIOVALIDITA AND NVL(DATAFINEVALIDITA , SYSDATE)\r\n"
			+ "AND TSCANCELLAZIONE IS NULL \r\n"
			+ "AND CODICEREGIONE <> '00'"
			+ "AND CODICECOMUNE = '000000'"
			+ "AND TRIM(DESCRIZIONELUOGO) LIKE  '%'||:inputDescrizione||'%'", nativeQuery=true)
	public List<Luogo> getProvinceLuogoFatto(@Param(value = "inputLuogo") String inputLuogo,
									 		 @Param(value = "inputDataPf") LocalDate inputDataPf,
											 @Param(value = "inputDescrizione") String inputDescrizione);
	
	@Query(value="SELECT \r\n"
			+ "CODICELUOGO,\r\n"
			+ "DATAINIZIOVALIDITA,\r\n"
			+ "INLUOGO,\r\n"
			+ "TRIM(DESCRIZIONELUOGO) AS DESCRIZIONELUOGO,\r\n"
			+ "DATAFINEVALIDITA,\r\n"
			+ "CODICEREGIONE,\r\n "
			+ "CODICEPROVINCIA,\r\n"
			+ "CODICECOMUNE,\r\n"
			+ "CODICECATASTALE,\r\n"
			+ "SIGLAPROVINCIA,\r\n"
			+ "TSINSERIMENTO,\r\n"
			+ "TSCANCELLAZIONE,\r\n"
			+ "CODICELUOGOAREA,\r\n"
			+ "CODICELUOGOZONA,\r\n"
			+ "CODICELUOGORIFERIMENTO,\r\n"
			+ "SIGLALUOGOASF\r\n"
			+ "FROM DBLUOGHI.TLUOGO \r\n"
			+ "WHERE INLUOGO = :inputLuogo \r\n"
			+ "AND :inputDataPf BETWEEN DATAINIZIOVALIDITA AND NVL(DATAFINEVALIDITA , SYSDATE)\r\n"
			+ "AND TSCANCELLAZIONE IS NULL \r\n"
			+ "AND CODICEPROVINCIA = :codiceProvincia"
			+ "AND TRIM(DESCRIZIONELUOGO) LIKE  '%'||:inputDescrizione||'%'", nativeQuery=true)
	public List<Luogo> getComuniLuogoFatto(@Param(value = "inputLuogo") String inputLuogo,
									 	   @Param(value = "inputDataPf") LocalDate inputDataPf,
										   @Param(value = "codiceProvincia") String codiceProvincia,
										   @Param(value = "inputDescrizione") String inputDescrizione);
}
