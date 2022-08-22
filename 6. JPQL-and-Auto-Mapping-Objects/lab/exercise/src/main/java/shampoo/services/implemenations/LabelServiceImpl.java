package shampoo.services.implemenations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shampoo.entities.database.Label;
import shampoo.repository.LabelRepository;
import shampoo.services.interfaces.LabelService;

@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository labelRepository;


    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public void saveLabel(Label label) {


        this.labelRepository.save(label);


    }
}
