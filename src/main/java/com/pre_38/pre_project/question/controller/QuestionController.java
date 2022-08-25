package com.pre_38.pre_project.question.controller;

import com.pre_38.pre_project.dto.MultiResponseDto;
import com.pre_38.pre_project.dto.PageInfo;
import com.pre_38.pre_project.dto.SingleResponseDto;
import com.pre_38.pre_project.member.entity.Member;
import com.pre_38.pre_project.member.service.MemberService;
import com.pre_38.pre_project.question.dto.QuestionDto;
import com.pre_38.pre_project.question.entity.Question;
import com.pre_38.pre_project.question.mapper.QuestionMapper;
import com.pre_38.pre_project.question.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@Slf4j
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper mapper;
    private final MemberService memberService;

    public QuestionController(QuestionService questionService,
                              QuestionMapper mapper,
                              MemberService memberService){
        this.questionService = questionService;
        this.mapper = mapper;
        this. memberService = memberService;
    }

    //요구사항 2.1 추 후 페이지네이션 도입하여 2.6도 구현
    @GetMapping
    public ResponseEntity getQuestions(){
        List<Question> questions = questionService.findQuestions();
        List<QuestionDto.response> responses = mapper.questionsToQuestionResponses(questions);
        return new ResponseEntity<>(
                new MultiResponseDto<>(responses), HttpStatus.OK
        );
    }


    //요구사항 2.2
    @PostMapping("/ask")
    public ResponseEntity postQuestion(@Valid @RequestBody QuestionDto.Post questionPost){
        Question question = mapper.questionPostToQuestion(questionPost);
        Member member = memberService.findMember(questionPost.getName());
        question.setMember(member);

        Question posted = questionService.createQuestion(question);
        QuestionDto.response response = mapper.questionToQuestionResponse(posted);


        return new ResponseEntity<>(
                new SingleResponseDto<>(response),HttpStatus.CREATED
        );
    }

    //요구사항 2.3
    @DeleteMapping("/{question-id}")
    public ResponseEntity deleteQuestion(@PathVariable("question-id") @Positive long questionId){
        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //요구사항 2.5
    @GetMapping("/{question-id}")
    public ResponseEntity getQuestion(@PathVariable("question-id") @Positive long questionId){
        Question question = questionService.findQuestion(questionId);
        QuestionDto.response response = mapper.questionToQuestionResponse(question);

        return new ResponseEntity<>(
                new SingleResponseDto<>(response),HttpStatus.OK
        );
    }
}
