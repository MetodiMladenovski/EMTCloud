package mk.ukim.finki.sharedkernel.domain.events.company;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import mk.ukim.finki.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.sharedkernel.domain.events.DomainEvent;

@Getter
public class AddEmployeeEvent extends DomainEvent {
    private String companyId;

    public AddEmployeeEvent(String topic) {
        super(TopicHolder.TOPIC_ADD_EMPLOYEE_TO_COMPANY);
    }

    @JsonCreator
    public AddEmployeeEvent(@JsonProperty("topic") String topic,
                            @JsonProperty("companyId") String companyId) {
        super(TopicHolder.TOPIC_ADD_EMPLOYEE_TO_COMPANY);
        this.companyId = companyId;
    }
}
