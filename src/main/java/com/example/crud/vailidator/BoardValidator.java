package com.example.crud.vailidator;

import com.example.crud.model.Board;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class BoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object target, org.springframework.validation.Errors errors) {
        Board b = (Board) target;
        if(StringUtils.isEmpty(((Board) b).getContent())){
            errors.rejectValue("content", "key", "내용을 입력하세요.");
        }

    }


}
