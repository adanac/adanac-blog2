    <!-- 主题内容模块 -->
    <div class="main-div">
        <h1>
            全部文章
        </h1>
    <#if pageArticles??>
        <#list pageArticles as article>
            <div class="blogs">
                <figure><img src="${article.icon}"></figure>
                <ul>
                    <h3><a href="${base}${article.url}">${article.subject}</a></h3>
                    <p>
                    ${article.summary}...
                    </p>
                    <p class="autor">
                        <span class="username_bg_image float_left"><a href="#">${article.username}</a></span>
                        <span class="time_bg_image float_left">${article.create_date?substring(0,10)}</span>
                        <span class="access_times_bg_image float_right">浏览（${article.access_times}）</span>
                        <span class="comment_times_bg_image float_right">评论（${article.comment_times}）</span>
                    </p>
                </ul>
            </div>

        </#list>
        <#include "/common/page.ftl">
    </#if>
    </div>
