package fr.dauphine.miageif.msa.Biblio.Repository;

import fr.dauphine.miageif.msa.Biblio.Lecteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LecteurRepository extends JpaRepository<Lecteur, Long> {
    Lecteur findById(String id);

    List<Lecteur> findAllByNom(String Nom);

    void deleteById(String id);

    boolean existsById(String id);


}
