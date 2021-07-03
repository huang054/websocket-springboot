package com.hj.websocket.mvc.autoConfig;

import com.hj.websocket.mvc.handler.DefaultWebSocketHandler;
import com.hj.websocket.mvc.path.PathScannerRegistrar;
import com.hj.websocket.mvc.processor.WebSocketBeanPostProcessor;
import org.springframework.context.annotation.Import;

@Import({PathScannerRegistrar.class, WebSocketBeanPostProcessor.class, DefaultWebSocketHandler.class})
public class WebSocketAutoConfig {
}
