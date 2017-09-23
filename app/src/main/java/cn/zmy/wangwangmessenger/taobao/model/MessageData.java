package cn.zmy.wangwangmessenger.taobao.model;

/**
 * Created by zmy on 2017/9/20.
 */

public class MessageData
{
    private String talkTo;
    private String domain;
    private String content;

    public MessageData()
    {
        this.domain = "cntaobao";
    }

    public static MessageData of(String talkTo, String content)
    {
        MessageData messageData = new MessageData();
        messageData.setTalkTo(talkTo);
        messageData.setContent(content);

        return messageData;
    }

    public String getTalkTo()
    {
        return talkTo;
    }

    public void setTalkTo(String talkTo)
    {
        this.talkTo = talkTo;
    }

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
