package com.example.twisterclient.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class GeneralAccountDTO {
    public enum Request {
        ;

        public static class Setting {
            @NotBlank
            @Length(min = 3, max = 64)
            @JsonProperty
            String nickname;

            public Setting() {
            }

            public Setting(String nickname) {
                this.nickname = nickname;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
