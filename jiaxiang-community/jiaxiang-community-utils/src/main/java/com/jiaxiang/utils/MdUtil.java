package com.jiaxiang.utils;

import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class MdUtil {


    /**
     * 将Markdown文件转换为HTML格式字符串
     * @param mdFilePath Markdown文件路径
     * @return 转换后的HTML字符串，转换失败时返回空字符串
     * @throws IllegalArgumentException 当文件路径为null或文件不存在时抛出
     */
    public static String mdFile2Html(String mdFilePath) {
        // 参数验证
        if (mdFilePath == null || mdFilePath.isEmpty()) {
            throw new IllegalArgumentException("Markdown文件路径不能为空");
        }

        Path path = Paths.get(mdFilePath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("Markdown文件不存在: " + mdFilePath);
        }

        try {
            // 使用try-with-resources读取文件内容
            String markdown;
            try (InputStream inputStream = Files.newInputStream(path);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                markdown = reader.lines().collect(Collectors.joining("\n"));
            }

            // 配置解析器选项以提高性能和功能
            MutableDataSet options = new MutableDataSet()
                    .set(Parser.EXTENSIONS, Arrays.asList(
                            TablesExtension.create(),
                            StrikethroughExtension.create(),
                            TaskListExtension.create()
                    ))
                    .set(HtmlRenderer.SOFT_BREAK, "<br />")
                    .set(Parser.PARSE_INNER_HTML_COMMENTS, true);

            // 解析Markdown为HTML
            Parser parser = Parser.builder(options).build();
            Node document = parser.parse(markdown);
            HtmlRenderer renderer = HtmlRenderer.builder(options).build();
            String htmlBody = renderer.render(document);

            // 仅在debug级别记录详细内容
            if (log.isDebugEnabled()) {
                log.debug("Markdown文件已转换为HTML，文件路径: {}", mdFilePath);
            }

            return htmlBody;
        } catch (IOException e) {
            log.error("Markdown转HTML失败: {}", mdFilePath, e);
            return ""; // 返回空字符串而不是null
        }
    }

    public static String mdSting2Html(String markdown){
        // 配置解析器选项以提高性能和功能
        MutableDataSet options = new MutableDataSet()
                .set(Parser.EXTENSIONS, Arrays.asList(
                        TablesExtension.create(),
                        StrikethroughExtension.create(),
                        TaskListExtension.create()
                ))
                .set(HtmlRenderer.SOFT_BREAK, "<br />")
                .set(Parser.PARSE_INNER_HTML_COMMENTS, true);

        // 解析Markdown为HTML
        Parser parser = Parser.builder(options).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        String htmlBody = renderer.render(document);

        // 仅在debug级别记录详细内容
        if (log.isDebugEnabled()) {
            log.debug("Markdown内容已转换为HTML");
        }

        return htmlBody;
    }
}
