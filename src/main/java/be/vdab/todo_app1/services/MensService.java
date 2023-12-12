package be.vdab.todo_app1.services;

import be.vdab.todo_app1.dto.NieuweMens;
import be.vdab.todo_app1.domain.Mens;
import be.vdab.todo_app1.repositories.MensRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MensService {

    private final MensRepository mensRepository;

    public MensService(MensRepository mensRepository) {
        this.mensRepository = mensRepository;
    }

    @Transactional
    public Mens create(NieuweMens nieuweMens){
        var mens = new Mens(nieuweMens.voornaam(), nieuweMens.familienaam());
        mensRepository.save(mens);
        return mens;
    }

    public Optional<Mens> findById(long id) {
        return mensRepository.findById(id);
    }

}
