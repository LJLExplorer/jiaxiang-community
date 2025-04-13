class MarkdownDriver {
    static markdownToHtml(markdown) {
        // 1. 将输入字符串按行分割为数组
        const lines = markdown.split(/\r?\n/);
        // 用于存放输出 HTML 的数组
        let htmlLines = [];
        // 临时变量记录当前是否处于列表中
        let inOrderedList = false;
        let inUnorderedList = false;
        // 辅助函数，用于结束列表块
        const closeLists = () => {
            if (inOrderedList) {
                htmlLines.push('</ol>');
                inOrderedList = false;
            }
            if (inUnorderedList) {
                htmlLines.push('</ul>');
                inUnorderedList = false;
            }
        };
        // 2. 遍历每一行，对每行内容进行匹配转换
        lines.forEach(line => {
            // 去除首尾空白
            let trimmed = line.trim();
            if (!trimmed) {
                // 空行：结束当前的列表，并插入空段落（可选）
                closeLists();
                htmlLines.push('');
                return;
            }
            // --- 标题转换 ---
            // # 标题：一级标题
            if (/^#\s+(.*)/.test(trimmed)) {
                closeLists();
                const content = trimmed.replace(/^#\s+/, '');
                htmlLines.push(`<h1>${content}</h1>`);
                return;
            }
            // ## 标题：二级标题
            if (/^##\s+(.*)/.test(trimmed)) {
                closeLists();
                const content = trimmed.replace(/^##\s+/, '');
                htmlLines.push(`<h2>${content}</h2>`);
                return;
            }
            // ### 标题：三级标题
            if (/^###\s+(.*)/.test(trimmed)) {
                closeLists();
                const content = trimmed.replace(/^###\s+/, '');
                htmlLines.push(`<h3>${content}</h3>`);
                return;
            }
            // --- 有序列表 --- (检测格式如 1、... 或 1. ... )
            if (/^\d+[\.\、]\s+(.*)/.test(trimmed)) {
                if (!inOrderedList) {
                    closeLists();
                    htmlLines.push('<ol>');
                    inOrderedList = true;
                }
                const content = trimmed.replace(/^\d+[\.\、]\s+/, '');
                htmlLines.push(`<li>${content}</li>`);
                return;
            }
            // --- 无序列表 --- (检测 -、* 或 + 开头)
            if (/^[-\*\+]\s+(.*)/.test(trimmed)) {
                if (!inUnorderedList) {
                    closeLists();
                    htmlLines.push('<ul>');
                    inUnorderedList = true;
                }
                const content = trimmed.replace(/^[-\*\+]\s+/, '');
                htmlLines.push(`<li>${content}</li>`);
                return;
            }
            // 默认：段落
            closeLists();
            // 3. 处理行内 Markdown 语法
            let processed = trimmed
                // 处理粗体：**text**
                .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
                // 处理斜体：*text*
                .replace(/\*(.*?)\*/g, '<em>$1</em>');
            // 4. 包裹为段落<p>
            htmlLines.push(`<p>${processed}</p>`);
        });
        // 如果还有未关闭的列表，则关闭
        closeLists();
        // 5. 将所有 HTML 行组合为最终字符串
        return htmlLines.join('\n');
    }
    static markdownToHtmlOld(markdown) {
        // 将 # 标题转换为 <h1>
        markdown = markdown.replace(/^#\s+(.*)/gm, "<h1>$1</h1>");
        // 将数字列表转换为 <ol> 和 <li>
        markdown = markdown.replace(/^\d+\.\s+(.*)/gm, "<li>$1</li>");
        markdown = markdown.replace(/<li>/g, "<ol><li>").replace(/<\/li>/g, "</li></ol>");
        // 将 ​**文本** 转换为 <strong>
        markdown = markdown.replace(/\*\*(.*?)\*\*/g, "<strong>$1</strong>");
        // 将 - 转换为 <ul> 和 <li>
        markdown = markdown.replace(/-\s+(.*)/gm, "<li>$1</li>");
        markdown = markdown.replace(/<li>/g, "<ul><li>").replace(/<\/li>/g, "</li></ul>");
        return markdown;
    }
}
export default MarkdownDriver;
