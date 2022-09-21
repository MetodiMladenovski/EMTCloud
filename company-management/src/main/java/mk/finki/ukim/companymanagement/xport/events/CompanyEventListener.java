package mk.finki.ukim.companymanagement.xport.events;

import lombok.AllArgsConstructor;
import mk.finki.ukim.companymanagement.domain.model.CompanyId;
import mk.finki.ukim.companymanagement.service.CompanyService;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.sharedkernel.domain.events.company.AddEmployeeEvent;
import mk.ukim.finki.sharedkernel.domain.events.storage.IncreaseBucketEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyEventListener {

    private final CompanyService companyService;

    @KafkaListener(topics = TopicHolder.TOPIC_ADD_EMPLOYEE_TO_COMPANY, groupId = "storageManagement")
    public void consumeIncreaseBucketEvent(String jsonMessage) {
        try {
            AddEmployeeEvent event = DomainEvent.fromJson(jsonMessage, AddEmployeeEvent.class);
            companyService.addEmployee(CompanyId.of(event.getCompanyId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

