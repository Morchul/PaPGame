package com.morchul.helper.json;

import com.morchul.json.JSONConverter;
import com.morchul.json.SimpleJSONConverter;

public class ClientJSONConverter {
    public static final JSONConverter simpleStaticConverter = new SimpleJSONConverter(new ClientJSONArrayHelper());
}
