package it.interno.luoghi.repository.specifications;

import it.interno.luoghi.entity.TLuogoDettaglio;
import it.interno.luoghi.entity.TLuogoDettaglio_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class TLuogoDettaglioSpecifications {

    public static Specification<TLuogoDettaglio> codiceLuogoEqual(Integer codiceLuogo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(TLuogoDettaglio_.codiceLuogo),  codiceLuogo) ;
    }

    public static Specification<TLuogoDettaglio> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(TLuogoDettaglio_.dataInizioValidita), dataRif);
    }

    public static Specification<TLuogoDettaglio> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(TLuogoDettaglio_.dataFineValidita), ld),
                    dataRif
            );
        };

    }

    public static Specification<TLuogoDettaglio> tsCancellazioneIsNull() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get(TLuogoDettaglio_.tsCancellazione)));
    }
}
