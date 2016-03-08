/**
 * Created by YanJun on 2016/2/1.
 */
$(document).ready(function() {
    $("#userLoginForm").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'The email is required and cannot be empty'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    },
                    remote: {
                        message: 'The username is not available',
                        url: '/SpringStudy/checkLoginEmail'
                    }
                }
            },
            password: {
                message: 'The password is not valid',
                trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: 'The password is required and cannot be empty'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: 'The password must be more than 6 and less than 10 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'The password can only consist of alphabetical, number and underscore'
                    }

                }
            }
        }
    });
});