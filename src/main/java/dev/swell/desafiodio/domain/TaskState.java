package dev.swell.desafiodio.domain;

public enum TaskState {
    COMPLETED("Completada"),
    STARTED("Iniciada"),
    PENDING("Pendente"),
    CANCELED("Cancelada"),
    NOT_STARTED("Não iniciada");

    private final String title;

    TaskState(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}


