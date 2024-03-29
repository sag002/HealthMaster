package saurabh.ghodake.healthydiet.news;

import android.content.Context;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import saurabh.ghodake.healthydiet.R;


public class NewsUtils {
    public static ArrayList<NewsBean> getAllNews(Context context) {
        ArrayList<NewsBean> arrayList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            NewsBean newsBean = new NewsBean();
            newsBean.title = "How to stay fit forever: 25 tips to keep moving when life gets in the way";
            newsBean.des = "Can you carry on exercising when your motivation slips, the weather gets worse or your " +
                    "schedule becomes overwhelming? Experts and Guardian readers give their best advice ";
            newsBean.icon = ContextCompat.getDrawable(context, R.drawable.how_to_stay_healthy);
            newsBean.news_url = "https://www.theguardian.com/lifeandstyle/2018/sep/12/how-to-stay-fit-for-ever-25-tips-keep-exercising-expert-advice";
            arrayList.add(newsBean);

            NewsBean newsBean0 = new NewsBean();
            newsBean0.title = "No Gym Required: How to Get Fit at Home";
            newsBean0.des = "You want to get fit. But you don't want to join a health club -- it's too expensive, " +
                    "there's no gym convenient to you, or maybe you're just the independent type. Or perhaps you're " +
                    "already a gym member, but your schedule has been too manic for you to get away.";
            newsBean0.icon = ContextCompat.getDrawable(context, R.drawable.gym);
            newsBean0.news_url = "https://www.webmd.com/fitness-exercise/features/no-gym-required-how-to-get-fit-at-home#1";
            arrayList.add(newsBean0);

            NewsBean newsBean1 = new NewsBean();
            newsBean1.title = "How to Stay Fit and Beautifully Healthy You'll have more energy,";
            newsBean1.des = "There are a lot of benefits to staying physically fit. You'll have more energy," +
                    "you'll look great, and you'll notice improvements in your overall health.";
            newsBean1.icon = ContextCompat.getDrawable(context, R.drawable.salad);
            newsBean1.news_url = "https://www.wikihow.com/Stay-Fit-and-Beautifully-Healthy";
            arrayList.add(newsBean1);

            NewsBean newsBean2 = new NewsBean();
            newsBean2.title = "How much food should my child be eating? And how can I get them to eat more healthily?";
            newsBean2.des = "Children need healthy food in the right amount so they get all the nutrients needed to grow, learn and thrive.";
            newsBean2.icon = ContextCompat.getDrawable(context, R.drawable.healthnews3);
            newsBean2.news_url = "https://theconversation.com/how-much-food-should-my-child-be-eating-and-how-can-i-get-them-to-eat-more-healthily-130470";
            arrayList.add(newsBean2);

            NewsBean newsBean3 = new NewsBean();
            newsBean3.title = "Hormone diets are all the rage, but do they actually work?";
            newsBean3.des = "When it comes to losing weight and getting healthy, there is never a shortage of diet and fitness" +
                    " crazes claiming to hold the secret to easy, sustainable weight loss. Some of the most recent popular diet" +
                    " crazes include the ketogenic diet (low carbohydrate, high fat), the carnivore diet (only eating meat and other" +
                    " animal products), and intermittent fasting (eating only within a strict timeframe or on certain days).";
            newsBean3.icon = ContextCompat.getDrawable(context, R.drawable.healthnews4);
            newsBean3.news_url = "https://theconversation.com/hormone-diets-are-all-the-rage-but-do-they-actually-work-122744";
            arrayList.add(newsBean3);

            NewsBean newsBean4 = new NewsBean();
            newsBean4.title = "First-year uni can add 4kg to your weight. Here’s how universities can scale that back";
            newsBean4.des = "Students typically gain weight in their first year of university. There are expressions to " +
                    "reflect this knowledge, such as the “freshman 15” in North America (which assumes students gain 15 pounds, " +
                    "or around 8kg) and “freshman fatties” or “fresher five” in Australia and New Zealand.";
            newsBean4.icon = ContextCompat.getDrawable(context, R.drawable.healthnews5);
            newsBean4.news_url = "https://theconversation.com/first-year-uni-can-add-4kg-to-your-weight-heres-how-universities-can-scale-that-back-115922";
            arrayList.add(newsBean4);

            NewsBean newsBean5 = new NewsBean();
            newsBean5.title = "The exercise conundrum: sometimes active people put on more weight than couch potatoes – here’s why";
            newsBean5.des = "Governments are always telling us to eat less and exercise more to be healthier, but this presents " +
                    "an obvious problem. Being active is liable to make you hungrier, so there’s a risk you end up eating extra to" +
                    " compensate, and putting on more weight than if you’d never got off the sofa in the first place.";
            newsBean5.icon = ContextCompat.getDrawable(context, R.drawable.healthnews6);
            newsBean5.news_url = "https://theconversation.com/the-exercise-conundrum-sometimes-active-people-put-on-more-weight-than-couch-potatoes-heres-why-114251";
            arrayList.add(newsBean5);

            NewsBean newsBean6 = new NewsBean();
            newsBean6.title = "Eat your vegetables – studies show plant-based diets are good for immunity";
            newsBean6.des = "The number of people in Australia who follow vegetarian or plant-based diets is growing rapidly. " +
                    "People might choose to be vegetarian for ethical, cultural or health-related reasons.";
            newsBean6.icon = ContextCompat.getDrawable(context, R.drawable.healthnews7);
            newsBean6.news_url = "https://theconversation.com/eat-your-vegetables-studies-show-plant-based-diets-are-good-for-immunity-107964";
            arrayList.add(newsBean6);

            NewsBean newsBean7 = new NewsBean();
            newsBean7.title = "10 ways to indulge and stay healthy this holiday season";
            newsBean7.des = "Before the holidays ruin your wellness plan and make you turn as green " +
                    "as the Grinch, try these 10 ways to help you stay on track and keep your festive spirit.";
            newsBean7.icon = ContextCompat.getDrawable(context, R.drawable.healthnews8);
            newsBean7.news_url = "https://theconversation.com/10-ways-to-indulge-and-stay-healthy-this-holiday-season-108330";
            arrayList.add(newsBean7);
        }
        return arrayList;
    }
}
