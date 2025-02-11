class Admin implements User {
    @Override
    public String getAccessLevel() {
        return "Pełny dostęp";
    }
}