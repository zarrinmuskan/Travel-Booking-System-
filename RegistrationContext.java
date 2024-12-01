public class RegistrationContext {
    private RegistrationStrategy strategy;

    public void setStrategy(RegistrationStrategy strategy) {
        this.strategy = strategy;
    }

    public void registerUser(User user) throws Exception {
        if (strategy == null) {
            throw new Exception("Registration strategy not set");
        }
        strategy.registerUser(user);
    }
}
