package tk.lwing.sample.lbsb.domain.exeptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String target, String value) {
        super(String.format("not found %s (%s)", target, value));
    }
}
