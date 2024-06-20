package com.example.drinkdeposit.personalizedResponse;

import java.io.Serializable;

public record PersonalizedResponse(int statusCode, Object mensage) implements Serializable {
}
