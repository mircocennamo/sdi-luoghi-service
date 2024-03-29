package it.interno.luoghi.service;

import it.interno.luoghi.dto.InfoPrefissiDto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

public interface TPrefissoLuoghiService {

    ArrayList<InfoPrefissiDto> getInfoPrefissi(String descrizione, String inLuogo, LocalDate dataRiferimento);

}
