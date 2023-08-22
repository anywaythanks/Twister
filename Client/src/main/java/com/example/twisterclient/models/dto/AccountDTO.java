package com.example.twisterclient.models.dto;

import com.example.twisterclient.models.Money;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class AccountDTO {
    public enum Request {
        ;

        public static class Setting {
            @NotBlank
            @Length(min = 3, max = 64)
            @JsonProperty
            Money.Type type;

            public Setting() {
            }

            public Setting(Money.Type type) {
                this.type = type;
            }

            public Money.Type getType() {
                return type;
            }

            public void setType(Money.Type type) {
                this.type = type;
            }
        }
    }
}
