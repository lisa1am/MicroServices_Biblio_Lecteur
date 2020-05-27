package fr.dauphine.miageif.msa.Biblio.Controller;

import fr.dauphine.miageif.msa.Biblio.Lecteur;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import fr.dauphine.miageif.msa.Biblio.Repository.LecteurRepository;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
public class LecteurController {

    @Autowired
    private Environment environment;

    @Autowired
    private LecteurRepository repository;

    @GetMapping("lecteurs/id/{id}")
    public Lecteur findById(@PathVariable String id){
        Lecteur lecteur = repository.findById(id);
        return lecteur;
    }

    @GetMapping("lecteurs/nom/{nom}")
    public List<Lecteur> findByNomPrenom(@PathVariable String nom){
        List<Lecteur> lecteurs = repository.findAllByNom(nom);
        return lecteurs;
    }

    @DeleteMapping("lecteurs/id/{id}")
    public String deleteByIsbn(@PathVariable String id){
        repository.deleteById(id);
        return "Le lecteur ayant l'id "+id+" a été supprimé de la base de données";
    }


    //HEADER : 'Content-type: application/json'
    @PutMapping("lecteurs/id/{id}")
    public String updateLivre(@RequestBody Lecteur lecteur, @PathVariable String id) {
        if (!repository.existsById(id)){
            return "Le lecteur n'existe pas dans la base de données !";
        }else{
            Lecteur lecteurEnBase = repository.findById(id);
            if(lecteur.getGenre() == null){
                lecteur.setGenre(lecteurEnBase.getGenre());
            }
            if(lecteur.getNom() == null){
                lecteur.setNom(lecteurEnBase.getNom());
            }
            if(lecteur.getPrenom() == null){
                lecteur.setPrenom(lecteurEnBase.getPrenom());
            }
            if(lecteur.getDate_naissance() == null){
                lecteur.setDate_naissance(lecteurEnBase.getDate_naissance());
            }
            if(lecteur.getAdresse() == null){
                lecteur.setAdresse(lecteurEnBase.getAdresse());
            }
            repository.save(lecteur);
            return "Le lecteur ayant l'Id "+id+" a été mis à jour avec succès";
        }

    }

    @PostMapping("lecteurs/")
    public String addLecteur(@RequestBody Lecteur lecteur){
        if (repository.existsById(lecteur.getId()))
            return "Le lecteur existe déjà dans la base de données !";

        repository.save(lecteur);
        return "Le lecteur a été enregistré avec succès";
    }
}
