package it.interno.luoghi.repository.specifications;

import it.interno.luoghi.entity.Luogo;
import it.interno.luoghi.entity.Luogo_;
import it.interno.luoghi.enumeration.TipoLuogo;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class LuogoSpecifications{

    public static final String ITALIA = "ITALIA";

    public static Specification<Luogo> cLuoEquals(Integer codiceLuogo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Luogo_.codiceLuogo), codiceLuogo);
    }

    public static Specification<Luogo> desLuoEquals(String desLuo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.trim(root.get(Luogo_.descrizioneLuogo)),  desLuo);
    }
    public static Specification<Luogo> siglaLuogoAsfEquals(String desLuo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.trim(root.get(Luogo_.siglaLuogoAsf)),  desLuo);
    }

    public static Specification<Luogo> desLuoLike(String desLuo){
    		return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Luogo_.descrizioneLuogo),  desLuo + "%");
    }

    public static Specification<Luogo> inLuoEquals(String inLuo) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Luogo_.inLuogo), inLuo);
    }

    public static Specification<Luogo> inLuoEqualsCodNations() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(criteriaBuilder.equal(root.get(Luogo_.inLuogo), TipoLuogo.NAZIONI.getCodTipoLuogo()));

    }

    public static Specification<Luogo> desLuoNotItaly() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(criteriaBuilder.notEqual(criteriaBuilder.trim(root.get(Luogo_.descrizioneLuogo)), ITALIA));
    }

    public static Specification<Luogo> retrievePlacesForDocuments(String desLuo, String inLuo, LocalDate dataRif) {
        Specification<Luogo> whereClause = Specification.where(tsCancellazioneIsNull()
                                                        .and(dataLessThanDIniVal(dataRif))
                                                        .and(dataRifGreaterThanDFinVal(dataRif))
                                                        .and(desLuoLike(desLuo)));
        if (!TipoLuogo.NAZIONI.getCodTipoLuogo().equals(inLuo))
            return whereClause.and(inLuoEquals(inLuo).or(inLuoEqualsCodNations().and(desLuoNotItaly())));

        return whereClause.and(inLuoEquals(inLuo));
    }

    public static Specification<Luogo> dataLessThanDIniVal(LocalDate dataRif) {
        return (root, query, criteriaBuilder) ->  criteriaBuilder.lessThanOrEqualTo(root.get(Luogo_.dataInizioValidita), dataRif);
    }

    public static Specification<Luogo> dataRifGreaterThanDFinVal(LocalDate dataRif) {

        return (root, query, criteriaBuilder) -> {
            LocalDate ld = LocalDate.parse("9999-12-31") ;
            return criteriaBuilder.greaterThanOrEqualTo(
                    criteriaBuilder.coalesce(root.get(Luogo_.dataFineValidita), ld),
                    dataRif
            );
        };

    }

    public static Specification<Luogo> tsCancellazioneIsNull() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get(Luogo_.tsCancellazione)));
    }

    public static Specification<Luogo> sglPrvEquals(String sglPrv) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Luogo_.siglaProvincia),  sglPrv);
    }
    
    public static Specification<Luogo> codPrvEquals(String codPrv) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Luogo_.codiceProvincia),  codPrv);
    }

    public static Specification<Luogo> retrieveLuoghiComuniNazioniDiversiDaItalia(String desLuo, LocalDate dataRif) {
        Specification<Luogo> whereClause = Specification.where(tsCancellazioneIsNull()
                .and(dataLessThanDIniVal(dataRif))
                .and(dataRifGreaterThanDFinVal(dataRif)));

        return whereClause.and((inLuoEqualsCodNations().and(desLuoNotItaly()).and(desLuoLike(desLuo)))
                .or(inLuoEquals(TipoLuogo.COMUNI.getCodTipoLuogo()).and(desLuoLike(desLuo))));
    }

}
