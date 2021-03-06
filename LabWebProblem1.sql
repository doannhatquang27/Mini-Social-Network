USE [master]
GO
/****** Object:  Database [LabWebProblem1]    Script Date: 29-Sep-20 7:45:46 PM ******/
CREATE DATABASE [LabWebProblem1]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LabWebProblem1', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\LabWebProblem1.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'LabWebProblem1_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\LabWebProblem1_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [LabWebProblem1] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LabWebProblem1].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LabWebProblem1] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LabWebProblem1] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LabWebProblem1] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LabWebProblem1] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LabWebProblem1] SET ARITHABORT OFF 
GO
ALTER DATABASE [LabWebProblem1] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LabWebProblem1] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LabWebProblem1] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LabWebProblem1] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LabWebProblem1] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LabWebProblem1] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LabWebProblem1] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LabWebProblem1] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LabWebProblem1] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LabWebProblem1] SET  DISABLE_BROKER 
GO
ALTER DATABASE [LabWebProblem1] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LabWebProblem1] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LabWebProblem1] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LabWebProblem1] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LabWebProblem1] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LabWebProblem1] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LabWebProblem1] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LabWebProblem1] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [LabWebProblem1] SET  MULTI_USER 
GO
ALTER DATABASE [LabWebProblem1] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LabWebProblem1] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LabWebProblem1] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LabWebProblem1] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [LabWebProblem1] SET DELAYED_DURABILITY = DISABLED 
GO
USE [LabWebProblem1]
GO
/****** Object:  Table [dbo].[tblArticle]    Script Date: 29-Sep-20 7:45:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblArticle](
	[articleId] [int] NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[title] [nvarchar](100) NOT NULL,
	[description] [nvarchar](max) NOT NULL,
	[publishedDate] [date] NOT NULL,
	[artImage] [nvarchar](100) NULL,
	[artStatus] [bit] NOT NULL,
 CONSTRAINT [PK_tblArticle] PRIMARY KEY CLUSTERED 
(
	[articleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblComment]    Script Date: 29-Sep-20 7:45:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblComment](
	[commentId] [int] NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[articleId] [int] NOT NULL,
	[commentContent] [nvarchar](max) NOT NULL,
	[cmtDate] [datetime] NOT NULL,
	[cmtStatus] [bit] NOT NULL,
 CONSTRAINT [PK_tblComment] PRIMARY KEY CLUSTERED 
(
	[commentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblEmotion]    Script Date: 29-Sep-20 7:45:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblEmotion](
	[email] [nvarchar](50) NOT NULL,
	[articleId] [int] NOT NULL,
	[reactDate] [datetime] NOT NULL,
	[reactStatus] [bit] NULL,
 CONSTRAINT [PK_tblEmotion] PRIMARY KEY CLUSTERED 
(
	[email] ASC,
	[articleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblNotification]    Script Date: 29-Sep-20 7:45:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblNotification](
	[email] [nvarchar](50) NOT NULL,
	[articleId] [int] NOT NULL,
	[ordinal] [int] NOT NULL,
	[date] [datetime] NOT NULL,
	[type] [bit] NOT NULL,
	[noStatus] [bit] NOT NULL,
 CONSTRAINT [PK_tblNotification] PRIMARY KEY CLUSTERED 
(
	[email] ASC,
	[articleId] ASC,
	[ordinal] ASC,
	[type] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 29-Sep-20 7:45:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[email] [nvarchar](50) NOT NULL,
	[name] [nvarchar](50) NOT NULL,
	[password] [nvarchar](64) NOT NULL,
	[role_isMember] [bit] NOT NULL,
	[status_isActivated] [bit] NOT NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (1, N'doannhatquang27@gmail.com', N'If You Only Have so Much Energy, Why Waste It?', N'The best way to use your energy is to redirect it into one place rather than multiple areas. This choice compounds all your energy and increases the impact you get from your results.', CAST(N'2020-09-17' AS Date), NULL, 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (2, N'doannhatquang27@gmail.com', N'What If You Treated Your Life like a Landing Page?', N'A landing page for non-tech folk is a single website page where there is only one button you can click.
There are no other links, pages, options, products, services, distractions or alternate paths. You either click the button on the landing page or you don’t.
This idea got me thinking. What if you treated your life like a landing page? What if you only offered up one option when given the chance to offer endless options? What would happen?', CAST(N'2020-09-17' AS Date), NULL, 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (3, N'doannhatquang27@gmail.com', N'When You Choose One Option You Make a Decision', N'When I meet a writer and they email me a link to their Linktree full of links, I want to go to the bathroom and throw up everywhere. By sending a link full of links you’re asking someone to use their precious energy to make a decision.', CAST(N'2020-09-19' AS Date), N'C:\Users\ASUS\Pictures\Wall Paper\scenic-view-of-sky-1367180.jpg', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (4, N'doannhatquang27@gmail.com', N'The Disciplined Power of Making a Single Choice', N'Supplying one link vs. two links is life-changing.
Imagine you have a social media profile and you only offer one link in your bio. Or imagine you email 100,000 people and you only give them one link to your work. Or contemplate writing your career history for a potential employer and only giving them one link.', CAST(N'2020-09-21' AS Date), N'F:\picture.jpg', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (5, N'nhatquang27@gmail.com', N'Single Option Thinking', N'When you give a tactic a name it makes it easier to understand. Whenever I have the chance to gain momentum in one area of my life, I think to myself âHow do I apply single option thinking to this task?â
That one question switches on my ability to focus and cut through the noise of making strangers with busy lives even more exhausted.
Single option thinking helps people. When you help people, you create value.', CAST(N'2020-09-29' AS Date), N'C:\Users\ASUS\Pictures\Wall Paper\flower-vase-beside-notebook-and-pencils-3773389.jpg', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (6, N'nhatquang27@gmail.com', N'When You Choose One Option You Make a Decision', N'Offering multiple options happens because you canât make a decision. Choosing a single option requires an incredible amount of discipline.
To go with one link vs. giving multiple links is a superpower.
When I meet a writer and they email me a link to their Linktree full of links, I want to go to the bathroom and throw up everywhere. By sending a link full of links youâre asking someone to use their precious energy to make a decision.', CAST(N'2020-09-29' AS Date), N'', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (7, N'nhatquang27@gmail.com', N'Elon Muskâs 2 Rules For Learning Anything Faster', N'Learning is one of the overcommunicated but underleveraged tools of the common entrepreneur.
Everyone talks about methods of learning, but few people find realistic and authentic techniques that actually yield a net profit in the information and application categories.
Elon Musk has broken through that barrier with learning techniques that have proven successful not just once, but time and time again.
A good argument could be made that Musk has leveraged his learning by becoming a disruptor. He and his companies have shifted entire industries, including the transportation sector, the energy sector, and the space sector.', CAST(N'2020-09-29' AS Date), N'C:\Users\ASUS\Pictures\Saved Pictures\elon_musk_tesla_3036.jpg', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (8, N'nhatquang27@gmail.com', N'Identify the different parts of the tree', N'âOne bit of advice: it is important to view knowledge as sort of a semantic tree â make sure you understand the fundamental principles, i.e. the trunk and big branches, before you get into the leaves/details or there is nothing for them to hang on to.â', CAST(N'2020-09-29' AS Date), N'C:\Users\ASUS\Pictures\Saved Pictures\the_tree.jpg', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (9, N'nhatquang27@gmail.com', N'Connections power your learning', N'The brilliance of Elon Muskâs learning strategy isnât necessarily in his ability to understand core central concepts.

Many entrepreneurs over generations have had solids grasp on core tenets and principles.', CAST(N'2020-09-29' AS Date), N'', 0)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (10, N'nhatquang27@gmail.com', N'Exponential growth', N'Like any new system, it might take you a bit to get the hang of it. You might actually feel like you are learning slower than you did previously. That is okay. What you are actually doing is building the foundation for exponential growth.', CAST(N'2020-09-29' AS Date), N'', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (11, N'nhatquang27@gmail.com', N'What I Learned After Going From a Janitor to Building a 6-Figure Business', N'At my lowest point, I was mopping floors at a gym in Los Angeles for $8/hour. My family called me a âloser,â people criticized my goals, and I almost gave up several times.
But with no other choice, I kept going and spent years learning, taking risks, failing, and investing a lot of time and money.
Today, I run a successful remote business while traveling the world full-time.', CAST(N'2020-09-29' AS Date), N'C:\Users\ASUS\Pictures\Saved Pictures\Janitor-cleaning-generic-Getty.jpg', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (12, N'nhatquang27@gmail.com', N'1. Embrace the Butterfly Effect', N'The traditional approach to careers is a straight and linear path: Decide what you like, get a college degree in it, find a job in it, and rise the ranks. But that model is quite outdated, especially with the countless paths available nowadays.', CAST(N'2020-09-29' AS Date), N'', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (13, N'nhatquang27@gmail.com', N'2. Reframe Your Failures', N'Iâve been screwed by a few companies and clients before, yet those moments were some of the best things that happened to me. Why? Hereâs a lesson from the Dalai Lama that sums it up perfectly:', CAST(N'2020-09-29' AS Date), N'', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (14, N'nhatquang27@gmail.com', N'3. Prioritize Your life', N'In the summer of 2012, I was a janitor at a gym. Less than three years later, I was writing for Muscle & Fitness, Menâs Fitness, and Menâs Health and ran a growing marketing business.
I worked hard for it. But while I loved what I did, I also worked hard because I had no other optionâI had to succeed. I had to prioritize my career because, at that time, it was hurting other parts of my life: My mental and emotional health, finances, social life, etc.', CAST(N'2020-09-29' AS Date), N'', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (15, N'doannhatquang27@gmail.com', N'4. Take ActionâNow', N'I often hear people say something theyâre like: âIâm going to work a job I donât like for a few years, and then Iâm going to quit and finally do what I really love.â
But why wait? Why donât you just do what you love right now? If youâre going to do it later, you might as well get started now and save time.
Life is short. Nothing justifies wasting precious time on things you donât care about.', CAST(N'2020-09-29' AS Date), N'', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (16, N'doannhatquang27@gmail.com', N'5. Donât Ask For Permission', N'If you want to do something in your career (or life), instead of getting other peopleâs approval, just do it first and handle any disapproval later. But no one will ever give you permission to do what you want in life.
The best things in my career happened when I went after what I wanted without asking for anyoneâs approval. Have conviction. Go after what you want. And if people have a problem with it later, then you could ask for forgiveness. But donât let them stop you from living your dreams.', CAST(N'2020-09-29' AS Date), N'C:\Users\ASUS\Pictures\Wall Paper\evgeny-lazarenko-8HknSpvc1CU-unsplash.jpg', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (17, N'doannhatquang27@gmail.com', N'6. Invest In Yourself', N'The more Iâve spent on personal development, the more Iâve succeeded.
Invest in yourself and youâll reap massive rewards and dividendsâread books, attend seminars, take courses, meet mentors, and get good at what you do.
When learning, nothing beats real-world experience and shadowing experts in your industry. For example, when I wanted to become a personal trainer, I made the tough decision to get an unpaid internship at one of the top athletic facilities in Southern California for three months.', CAST(N'2020-09-29' AS Date), N'', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (18, N'doannhatquang27@gmail.com', N'7. Get Advice From The Right People', N'The right advice can transform your life. But with so many people giving advice, how do you know who to actually listen to?
Simple: Only ask advice from people whoâve done what you want to do successfully or have coached numerous people successfully. Never take advice from people who have no idea what theyâre talking about.
For example, Iâve had countless people offer advice on what I should do to grow my business, but those people never had their own business. So how would they actually know?', CAST(N'2020-09-29' AS Date), N'', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (19, N'doannhatquang27@gmail.com', N'8. Research Your Path Before You Start', N'The sad truth is many people spend years on a career path only to realize, once they achieve it, they hate it. And because of the âsunk cost fallacy,â theyâll stick with it because theyâve invested so much of their time, regardless of how much they dislike it.
Avoid this trap by researching the field ahead of time. Find at least 5 people in your target industry and talk to them. Ask them what they think about their job or business. Ask them what their day is like. Ask them about their likes and dislikes. Ask them what it takes to succeed. That way, you find out before you start and can decide if itâs right for you.', CAST(N'2020-09-29' AS Date), N'C:\Users\ASUS\Pictures\Wall Paper\green-succulent-plant-1031641.jpg', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (20, N'doannhatquang27@gmail.com', N'9. Crush Your Opportunities', N'In life, youâll get chances: Itâs what you do with them that matters.
Iâve spent countless hours on just one email because I knew how important it could be. I spent over 30 hours writing my first-ever article for a major publication because I realized it was a tremendous opportunity. All of which led to many more opportunities.
Sometimes, you only get one shotâso whether itâs a freelance job, a job interview, a work project, a client, etc. put in the work and go the extra mile.
For example, if you have a job interview, donât just write down your answers. Memorize them. Ask a friend to ask you questions and videotape yourself. Then watch it and try it again and again. Go above and beyond. (Few people will do it.)
Regardless of how established or new you are, the results can be exponential.', CAST(N'2020-09-29' AS Date), N'', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (21, N'doannhatquang27@gmail.com', N'10. Do Things For The Right Reasons', N'If you want to create a million-dollar business, become president of a company, or just work 4 hours per week, thatâs all fine. But before you go on the long journey to achieve it, look inside and ask why youâre doing it in the first place. The reality is while Iâve met many entrepreneurs and business owners who were amazing people, Iâve also met many who had questionable motivations.
Some were trying to prove themselves. Some were trying to pretend they were someone they werenât. Some used work to distract themselves and avoid confronting deeper problems. And a few were arrogant, cocky, and mean to others; they actually believed they were better those who werenât as successful in business.', CAST(N'2020-09-29' AS Date), N'', 1)
INSERT [dbo].[tblArticle] ([articleId], [email], [title], [description], [publishedDate], [artImage], [artStatus]) VALUES (22, N'doannhatquang27@gmail.com', N'11. Show Gratitude and Humility', N'For all my accomplishments, Iâve benefited from many kind, supportive, and generous people. And Iâm grateful for every person and event â good and bad â which pushed on my path.
Yes, I worked hard. But a lot of it wouldâve been wasted if it wasnât for people whoâve helped.
So for all your accomplishments, please do not I think that it was âall you.â
More importantly, make a sincere effort to thank each person who supported you.', CAST(N'2020-09-29' AS Date), N'', 1)
INSERT [dbo].[tblComment] ([commentId], [email], [articleId], [commentContent], [cmtDate], [cmtStatus]) VALUES (1, N'nhatquang27@gmail.com', 4, N'Great Picture !!!', CAST(N'2020-09-29 09:25:27.553' AS DateTime), 1)
INSERT [dbo].[tblComment] ([commentId], [email], [articleId], [commentContent], [cmtDate], [cmtStatus]) VALUES (2, N'nhatquang27@gmail.com', 3, N'Do not do it', CAST(N'2020-09-29 19:27:08.060' AS DateTime), 0)
INSERT [dbo].[tblEmotion] ([email], [articleId], [reactDate], [reactStatus]) VALUES (N'doannhatquang27@gmail.com', 7, CAST(N'2020-09-29 19:34:18.360' AS DateTime), 0)
INSERT [dbo].[tblEmotion] ([email], [articleId], [reactDate], [reactStatus]) VALUES (N'doannhatquang27@gmail.com', 8, CAST(N'2020-09-29 19:34:45.163' AS DateTime), 1)
INSERT [dbo].[tblEmotion] ([email], [articleId], [reactDate], [reactStatus]) VALUES (N'nhatquang27@gmail.com', 3, CAST(N'2020-09-29 09:26:20.393' AS DateTime), 0)
INSERT [dbo].[tblEmotion] ([email], [articleId], [reactDate], [reactStatus]) VALUES (N'nhatquang27@gmail.com', 4, CAST(N'2020-09-29 09:25:13.603' AS DateTime), 1)
INSERT [dbo].[tblEmotion] ([email], [articleId], [reactDate], [reactStatus]) VALUES (N'nhatquang27@gmail.com', 8, CAST(N'2020-09-29 19:41:47.450' AS DateTime), NULL)
INSERT [dbo].[tblNotification] ([email], [articleId], [ordinal], [date], [type], [noStatus]) VALUES (N'doannhatquang27@gmail.com', 7, -1, CAST(N'2020-09-29 19:34:18.363' AS DateTime), 1, 1)
INSERT [dbo].[tblNotification] ([email], [articleId], [ordinal], [date], [type], [noStatus]) VALUES (N'doannhatquang27@gmail.com', 8, -1, CAST(N'2020-09-29 19:34:45.173' AS DateTime), 1, 1)
INSERT [dbo].[tblNotification] ([email], [articleId], [ordinal], [date], [type], [noStatus]) VALUES (N'nhatquang27@gmail.com', 3, -1, CAST(N'2020-09-29 09:26:20.397' AS DateTime), 1, 1)
INSERT [dbo].[tblNotification] ([email], [articleId], [ordinal], [date], [type], [noStatus]) VALUES (N'nhatquang27@gmail.com', 3, 2, CAST(N'2020-09-29 19:27:08.077' AS DateTime), 0, 0)
INSERT [dbo].[tblNotification] ([email], [articleId], [ordinal], [date], [type], [noStatus]) VALUES (N'nhatquang27@gmail.com', 4, -1, CAST(N'2020-09-29 09:25:13.610' AS DateTime), 1, 1)
INSERT [dbo].[tblNotification] ([email], [articleId], [ordinal], [date], [type], [noStatus]) VALUES (N'nhatquang27@gmail.com', 4, 1, CAST(N'2020-09-29 09:25:27.567' AS DateTime), 0, 1)
INSERT [dbo].[tblNotification] ([email], [articleId], [ordinal], [date], [type], [noStatus]) VALUES (N'nhatquang27@gmail.com', 8, -1, CAST(N'2020-09-29 19:41:47.457' AS DateTime), 1, 0)
INSERT [dbo].[tblUser] ([email], [name], [password], [role_isMember], [status_isActivated]) VALUES (N'doannhatquang27@gmail.com', N'Doan Nhat Quang', N'c57bf93d578a2f22947496a3f0763d3c1e6d13b8bb8e93eac2ffdddbddf97297', 1, 1)
INSERT [dbo].[tblUser] ([email], [name], [password], [role_isMember], [status_isActivated]) VALUES (N'kojima@gmail.com', N'kojima haruna', N'79cc28a75f21d68d15fdb35f9a38892c5c5a9aedd5e214468d93ca7790873256', 1, 0)
INSERT [dbo].[tblUser] ([email], [name], [password], [role_isMember], [status_isActivated]) VALUES (N'kojima1@gmail.com', N'Kojima Haruna', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 1, 0)
INSERT [dbo].[tblUser] ([email], [name], [password], [role_isMember], [status_isActivated]) VALUES (N'nhatquang27@gmail.com', N'Doan Nguyen Nhat Quang', N'df026d4714f12bac8069b9c12a0f65fc469dd6e5b01d369debcabc6b04c12eb9', 1, 1)
ALTER TABLE [dbo].[tblArticle]  WITH CHECK ADD  CONSTRAINT [FK_tblArticle_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblArticle] CHECK CONSTRAINT [FK_tblArticle_tblUser]
GO
ALTER TABLE [dbo].[tblComment]  WITH CHECK ADD  CONSTRAINT [FK_tblComment_tblArticle] FOREIGN KEY([articleId])
REFERENCES [dbo].[tblArticle] ([articleId])
GO
ALTER TABLE [dbo].[tblComment] CHECK CONSTRAINT [FK_tblComment_tblArticle]
GO
ALTER TABLE [dbo].[tblComment]  WITH CHECK ADD  CONSTRAINT [FK_tblComment_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblComment] CHECK CONSTRAINT [FK_tblComment_tblUser]
GO
ALTER TABLE [dbo].[tblEmotion]  WITH CHECK ADD  CONSTRAINT [FK_tblEmotion_tblArticle] FOREIGN KEY([articleId])
REFERENCES [dbo].[tblArticle] ([articleId])
GO
ALTER TABLE [dbo].[tblEmotion] CHECK CONSTRAINT [FK_tblEmotion_tblArticle]
GO
ALTER TABLE [dbo].[tblEmotion]  WITH CHECK ADD  CONSTRAINT [FK_tblEmotion_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblEmotion] CHECK CONSTRAINT [FK_tblEmotion_tblUser]
GO
ALTER TABLE [dbo].[tblNotification]  WITH CHECK ADD  CONSTRAINT [FK_tblNotification_tblArticle] FOREIGN KEY([articleId])
REFERENCES [dbo].[tblArticle] ([articleId])
GO
ALTER TABLE [dbo].[tblNotification] CHECK CONSTRAINT [FK_tblNotification_tblArticle]
GO
ALTER TABLE [dbo].[tblNotification]  WITH CHECK ADD  CONSTRAINT [FK_tblNotification_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblNotification] CHECK CONSTRAINT [FK_tblNotification_tblUser]
GO
USE [master]
GO
ALTER DATABASE [LabWebProblem1] SET  READ_WRITE 
GO
