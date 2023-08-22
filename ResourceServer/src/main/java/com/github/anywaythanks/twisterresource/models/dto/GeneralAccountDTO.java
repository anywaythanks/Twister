package com.github.anywaythanks.twisterresource.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class GeneralAccountDTO {
    private interface Name {
        @NotBlank
        @Length(min = 1, max = 64)
        String getName();
    }

    private interface Uuid {
        @NotBlank
        String getUuid();
    }

    private interface Nickname {
        @NotBlank
        @Length(min = 3, max = 64)
        String getNickname();
    }

    private interface Id {
        @NotNull
        Long getId();
    }

    public enum Request {
        ;

        public static class Nickname implements GeneralAccountDTO.Nickname {
            String nickname;

            public Nickname(String nickname) {
                this.nickname = nickname;
            }

            @Override
            public String getNickname() {
                return nickname;
            }
        }

        public static class Create implements GeneralAccountDTO.Nickname {
            String nickname;

            @Override
            public String getNickname() {
                return nickname;
            }
        }

        public static class Name implements GeneralAccountDTO.Name {
            String name;

            protected Name() {
            }

            public Name(String name) {
                this.name = name;
            }

            @Override
            public String getName() {
                return name;
            }
        }
    }

    public enum Response {
        ;

        public static class Partial implements GeneralAccountDTO.Name, Nickname {
            String nickname;
            String name;

            protected Partial() {
            }

            public Partial(String name, String nickname) {
                this.name = name;
                this.nickname = nickname;
            }

            @Override
            public String getNickname() {
                return nickname;
            }

            @Override
            public String getName() {
                return name;
            }
        }

        public static class Public implements GeneralAccountDTO.Name, Nickname {
            String nickname;
            String name;

            protected Public() {
            }

            public Public(String name, String nickname) {
                this.name = name;
                this.nickname = nickname;
            }

            @Override
            public String getNickname() {
                return nickname;
            }

            @Override
            public String getName() {
                return name;
            }
        }

        public static class Id implements GeneralAccountDTO.Id, Uuid {
            Long id;
            String uuid;

            protected Id() {
            }

            public Id(Long id, String uuid) {
                this.id = id;
                this.uuid = uuid;
            }

            @Override
            public Long getId() {
                return id;
            }

            @Override
            public String getUuid() {
                return uuid;
            }
        }

        public static class Name implements GeneralAccountDTO.Name {
            String name;

            protected Name() {
            }

            public Name(String name) {
                this.name = name;
            }

            @Override
            public String getName() {
                return name;
            }
        }
    }
}
