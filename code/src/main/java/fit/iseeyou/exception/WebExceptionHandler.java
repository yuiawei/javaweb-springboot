package fit.iseeyou.exception;

import fit.iseeyou.common.domain.AjaxResult;
import fit.iseeyou.common.domain.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {
    @ExceptionHandler(UserAuthException.class)
    public AjaxResult userAuthExceptionHandler(Exception e) {
        return AjaxResult.error(HttpStatus.UNAUTHORIZED, e.getMessage());
    }
}
