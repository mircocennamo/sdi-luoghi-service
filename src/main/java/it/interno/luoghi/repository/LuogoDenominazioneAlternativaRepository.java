package it.interno.luoghi.repository;

import it.interno.luoghi.entity.TLuogoDenominazioneAlternativa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LuogoDenominazioneAlternativaRepository extends JpaRepository<TLuogoDenominazioneAlternativa, Integer> {

    List<TLuogoDenominazioneAlternativa> findBydescrizioneLuogo(String descrizioneLuogo);
}
