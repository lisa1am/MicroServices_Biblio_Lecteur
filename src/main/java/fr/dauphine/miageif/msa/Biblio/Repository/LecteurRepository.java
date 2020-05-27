package fr.dauphine.miageif.msa.Biblio.Repository;

import fr.dauphine.miageif.msa.Biblio.Lecteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LecteurRepository extends JpaRepository<Lecteur, Long> {
    Lecteur findByIsbn(String isbn);

    List<Lecteur> findAllByAuteur(String Auteur);

    void deleteByIsbn(String isbn);

    boolean existsByIsbn(String isbn);



}
