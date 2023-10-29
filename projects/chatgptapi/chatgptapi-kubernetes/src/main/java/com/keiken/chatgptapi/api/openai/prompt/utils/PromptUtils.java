package com.keiken.chatgptapi.api.openai.prompt.utils;

public class PromptUtils {

    public static String generateJson(String content) {
        String json = """
        {
            "model": "gpt-3.5-turbo",
            "messages": [
                {
                    "role": "user",
                    "content": "%s"
                }
            ],
            "temperature": 1,
            "max_tokens": 5,
            "top_p": 1,
            "frequency_penalty": 0,
            "presence_penalty": 0
        }
        """.formatted(content);
        return json;
    }

}
