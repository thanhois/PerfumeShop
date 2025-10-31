--drop database Perfume_Website 


CREATE DATABASE Perfume_Website 

USE [Perfume_Website]
-- Tạo bảng [dbo].[Users]
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1),
	[UserName] [nvarchar](50) PRIMARY KEY NOT NULL ,
	[FullName] [nvarchar](50) NOT NULL,
	[Password] [nvarchar](50) NOT NULL,
	[RoleID] [int] NOT NULL,
	[Image] [nvarchar](50) NULL,
	[Email] [nvarchar](50)  NULL,
	[BirthDay] date NOT NULL,
	[Address] [nvarchar](200) NULL,
	[Phone] [nvarchar](11) NOT NULL,
	[status] bit NOT NULL
)

INSERT INTO [dbo].[Users] ([UserName], [FullName], [Password], [Address],[Image], [Email], [BirthDay], [Phone], [RoleID], [status])
VALUES 
    ('john_doe', 'John Doe', 'password123', N'123 Main St, CityA', 'images/users/user.png', 'aaaa@gmail.com', '2003-08-09', '12345678901', 1, 1),
	('phuuthanh2003', N'Phùng Hữu Thành', '123', N'60 Nguyễn Văn Trỗi', 'images/users/thanh.png', 'phuuthanh20033@gmail.com', '2003-08-09', '0707064154', 1 , 1),
	('john_doeee', N'John Doe', 'password123', N'123 Main St, CityA', 'images/users/user.png', 'aaaa@gmail.com', '2003-08-09', '12345678901', 2, 1),
	('user1', N'Khoái ăn sang', 'password123', N'456 Oak St, CityB', 'images/users/user1.png', 'phunghthanh2203@gmail.com', '1995-02-15', '9876543210', 2, 1),
    ('user2', N'User Two', 'password456', N'789 Pine St, CityC', 'images/users/user2.png', 'user2@gmail.com', '1990-07-22', '1239874560', 2, 1),
    ('user3', N'User Three', 'password789', N'Chả sợ chi', 'images/users/user3.png', 'user3@gmail.com', '1988-11-30', '6543217890', 2, 1),
    ('user4', N'User Four', 'pass123word', N'202 Elm St, CityE', 'images/users/user4.png', 'user4@gmail.com', '1992-04-18', '7890123456', 2, 0),
    ('user5', N'Tối em có sang', 'pass456word', N'303 Birch St, CityF', 'images/users/user5.png', 'user5@gmail.com', '1998-09-03', '3456789012', 2, 1),
    ('user6', N'Nho từ điển', 'pass789word', N'404 Cedar St, CityG', 'images/users/user6.png', 'user6@gmail.com', '1993-12-25', '9012345678', 2, 1),
    ('user7', N'User Seven', 'pass789word', N'Ban lãnh đạo', 'images/users/user7.png', 'user7@gmail.com', '1991-06-10', '5678901234', 2, 1),
    ('user8', N'User Eight', 'pass123word', N'606 Elm St, CityI', 'images/users/user8.png', 'user8@gmail.com', '1994-03-27', '2345678901', 2, 1),
    ('user9', N'User Nine', 'pass456word', N'707 Oak St, CityJ', 'images/users/user9.png', 'user9@gmail.com', '1997-08-14', '8901234567', 2, 1),
    ('user10', N'Cai một hún', 'pass789word', N'Người đàn bà vu khống', 'images/users/user10.png', 'user10@gmail.com', '1996-01-05', '4567890123', 2, 1),
	('11ne', N'Tính anh thật tồi', 'pass123word', N'Có chỗ đứng', 'images/users/useR11.png', 'user4@gmail.com', '2003-04-18', '7890123456', 2, 0),
    ('12dau', N'Ngủ đi', 'pass456word', N'303 Birch St, CityF', 'images/users/user12.png', 'user5@gmail.com', '1998-09-03', '3456789012', 2, 1),
    ('13kia', N'Đàn ông đích thực', 'pass789word', N'404 Cedar St, CityG', 'images/users/user13.png', 'user6@gmail.com', '1993-12-25', '9012345678', 2, 1),
    ('14day', N'Thế em là mây', 'pass789word', N'505 Pine St, CityH', 'images/users/user14.png', 'user7@gmail.com', '1991-06-10', '5678901234', 2, 1),
    ('15do', N'Càng nặng ký', 'pass123word', N'Không dám bung cánh vì sợ em tung cánh', 'images/users/user15.png', 'user8@gmail.com', '1994-03-27', '2345678901', 2, 1),
    ('16roi', N'Tôi lấy vợ', 'pass456word', N'Một tay bắt cọp', 'images/users/user16.png', 'user9@gmail.com', '1997-08-14', '8901234567', 2, 1);

-- Tạo bảng [dbo].[Categories]
CREATE TABLE [dbo].[Categories](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](15) NOT NULL,
	[Description] [ntext] NULL,
	CONSTRAINT [PK_Categories] PRIMARY KEY([CategoryID])
)

INSERT INTO [dbo].[Categories] ([CategoryName], [Description])
VALUES 
    ('Men', N'Các quý ông tìm đến nước hoa để làm gì? Có lẽ là để thơm tho, nam tính và làm chỉn chu thêm phong cách của bản thân, phải chứ? Namperfume thấu hiểu các quý ông của 
	chúng ta, đem tới cho đấng mày râu những mùi hương tươm tất, gọn gàng, cuốn hút, đôi khi là quyền lực choáng ngợp, và chắc chắn không thể quên được sự bụi bặm phóng khoáng đặc
	trưng của phái mạnh.'),
    ('Women', N'Nước hoa từ những ngày đầu đã được tạo ra là để phục vụ cho phái đẹp, vì thế dường như trong thế giới mùi hương, những sự lựa chọn cho nữ giới là phong phú
	và nhiều màu sắc hơn cả. Là do vậy, namperfume luôn muốn đem đến cho các quý cô xinh đẹp những lựa chọn tuyệt vời, từ quyến rũ, sang trọng, quyền lực đến nhẹ nhàng, ngây thơ, 
	và không thể thiếu một chút gợi cảm lả lơi, ngả ngốn...'),
    ('Kids', N'Nước hoa cho bé thường được làm từ những thành phần thiên nhiên, đảm bảo an toàn và giúp mang đến cho bé mùi hương dịu nhẹ, dễ chịu. 
	Ngoài ra, sử dụng nước hoa còn giúp kích thích khứu giác của bé phát triển.'),
    ('Unisex', N'Nước hoa unisex là dòng nước hoa phù hợp cho mọi giới tính, dù là nam hay nữ cũng đều dùng chung sản phẩm này. Cùng một chái nước hoa nhưng khi mày râu dùng sẽ trở nên 
	lịch lãm, một nàng sỡ hữu nét quyến rũ yêu kều sẽ trở nên hấp dẫn hơn nữa.'),
	('Giftset', N'Giftset – hay còn gọi là bộ quà tặng, là tập hợp nhiều loại sản phẩm khác nhau. Chúng đều có tính ứng dụng cao để phục vụ cho công việc và cả trong cuộc sống hàng ngày. 
	Tất cả các sản phẩm trong bộ Giftset đều có liên quan đến nhau, tính thẩm mỹ cao để mang đến sự hài lòng cho người nhận.');


-- Tạo bảng [dbo].[Suppliers]
CREATE TABLE [dbo].[Suppliers](
	[SupplierID] [int] IDENTITY(1,1) NOT NULL,
	[CompanyName] [nvarchar](40) NOT NULL,
	[ContactName] [nvarchar](50) NULL,
	[Country] [nvarchar](50) NULL,
	[Phone] [nvarchar](24) NULL,
	[HomePage] [ntext] NULL,
	CONSTRAINT [PK_Suppliers] PRIMARY KEY([SupplierID])
)



INSERT INTO [dbo].[Suppliers] ([CompanyName], [ContactName], [Country], [Phone], [HomePage])
VALUES 
    ('Versace', 'Supplier Contact Versace', 'Italy', '+39 02 1234 5678', 'http://www.versace.com'),
    ('Hugo Boss', 'Supplier Contact Hugo Boss', 'Germany', '+49 30 9876 5432', 'http://www.hugoboss.com'),
    ('Jaguar', 'Supplier Contact Jaguar', 'United Kingdom', '+44 20 3456 7890', 'http://www.jaguar.com'),
    ('Armani', 'Supplier Contact Armani', 'Italy', '+39 02 2345 6789', 'http://www.armani.com'),
    ('Paco Rabanne', 'Supplier Contact Paco Rabanne', 'France', '+33 1 3456 7890', 'http://www.pacorabanne.com'),
    ('Ralph Lauren', 'Supplier Contact Ralph Lauren', 'USA', '+1 212 555 1234', 'http://www.ralphlauren.com'),
    ('Bvlgari', 'Supplier Contact Bvlgari', 'Italy', '+39 06 1234 5678', 'http://www.bvlgari.com'),
    ('Coach', 'Supplier Contact Coach', 'USA', '+1 212 555 6789', 'http://www.coach.com'),
    ('Kenzo', 'Supplier Contact Kenzo', 'France', '+33 1 8765 4321', 'http://www.kenzo.com'),
    ('D&G', 'Supplier Contact D&G', 'Italy', '+39 02 3456 7890', 'http://www.dolcegabbana.com'),
    ('Jean Paul Gaultier', 'Supplier Contact Jean Paul Gaultier', 'France', '+33 1 2345 6789', 'http://www.jeanpaulgaultier.com'),
    ('Ajmal', 'Supplier Contact Ajmal', 'United Arab Emirates', '+971 4 1234 5678', 'http://www.ajmalperfume.com'),
    ('Calvin Klein', 'Supplier Contact Calvin Klein', 'USA', '+1 212 555 7890', 'http://www.calvinklein.com'),
    ('The Body Shop', 'Supplier Contact The Body Shop', 'United Kingdom', '+44 20 9876 5432', 'http://www.thebodyshop.com'),
    ('Lattafa', 'Supplier Contact Lattafa', 'United Arab Emirates', '+971 6 2345 6789', 'http://www.lattafa.com');

-- Tạo bảng [dbo].[Products]
CREATE TABLE [dbo].[Products](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [nvarchar](100) NOT NULL,
	[SupplierID] [int] NULL,
	[CategoryID] [int] NULL,
	[QuantityPerUnit] [nvarchar](100) NULL,
	[UnitPrice] [money] NULL,
	[UnitsInStock] [smallint] NULL,
	[QuantitySold] [int] NULL,
	[StarRating] [smallint] NULL,
	[Discontinued] [bit] NULL,
	[image] [nvarchar](max) NULL,
	[describe] [nvarchar](max) NULL,
	[releaseDate] [date] NULL,
	[Discount] [float] NULL,
	[status] bit Not null,
	CONSTRAINT [PK_Products] PRIMARY KEY([ProductID]),
	CONSTRAINT [FK_Products_Supplier]
    FOREIGN KEY ([SupplierID])
    REFERENCES [dbo].[Suppliers] ([SupplierID]) ON DELETE SET NULL ON UPDATE CASCADE,
	CONSTRAINT [FK_Products_Category] 
    FOREIGN KEY ([CategoryID])
    REFERENCES [dbo].[Categories] ([CategoryID]) 
)

INSERT INTO [dbo].[Products] (
    [ProductName], 
    [SupplierID], 
    [CategoryID], 
    [QuantityPerUnit], 
    [UnitPrice], 
    [UnitsInStock], 
    [QuantitySold], 
    [StarRating], 
    [Discontinued],
	[image],
	[describe],
	[releaseDate],
	[Discount],
	[status]
	)
VALUES 
    -- 20 products for Men (https://missi.com.vn/danh-muc/nuoc-hoa-nam/)
    ('NAUTICA VOYAGE N-83 EDT', 1, 1, '100ml 75ml', 19.99, 100, 20, 5, 0, 'images/products/Men/1-1.png, images/products/Men/1-2.png', 
	N'Nautica Voyage N-83 Eau de Toilette for men là mùi hương mang đến cho bạn cảm giác tươi mát, và yếu tố nam tính và mạnh mẽ của phái mạnh. Mở đầu bằng sự kết hợp giữa Hương biển, Tinh dầu lá chanh và Bạc hà, tổ hợp hương thơm trong tầng hương đầu khiến không gian trở nên trong lành và chắc hẳn tầng hương này sẽ giúp những chàng trai thể hiện phong cách phóng khoáng và năng động.', '2023-10-02',0.7, 1),

    ('JEAN PAUL GAULTIER LE MALE ELIXIR', 2, 1, '125ml 50ml', 29.99, 50, 10, 3, 0, 'images/products/Men/2-1.png, images/products/Men/2-2.png', 
	N'Vị ngọt làm nên tính cách của Jean Paul Gaultier Le Male Elixir đến từ Vanilla và Mật ong. Vanilla bông phấn mịn màng, quyện cùng Mật ong sánh đặc, tạo nên tông vị mềm mại nhưng vẫn da diết vô cùng.', '2023-11-02',0.5, 1),

    ('NAUTICA VOYAGE SPORT EDT', 3, 1, '100ml 25ml 75ml', 39.99, 75, 15, 3, 0, 'images/products/Men/3-1.png, images/products/Men/3-2.png', 
	N'Nautica Voyage Sport mang đậm dấu ấn của phiên bản gốc 2006. Như thể Nauticađang kêu gọi chúng ta một lần nữa chèo trên con thuyền Gỗ và bắt lấy những con sóng xanh rì. Lấy cảm hứng từ tinh thần thể thao của những chàng trai, Nautica Voyage Sport công bố là một phiên bản mang đậm vẻ đẹp thể thao, năng động.', '2016-10-02',0.3, 1),

    ('MONTALE AOUD LEATHER EDP', 4, 1, '100ml', 49.99, 120, 30, 4, 0, 'images/products/Men/4-1.png, images/products/Men/4-2.png', 
	N'Montale Aoud Leather khai thác nét đẹp của nốt hương Da thuộc, Trầm hương và Nhụy nghệ tây. Nói là các yếu tố mạnh mẽ, nhưng Da thuộc và Trầm hương lại được Montale xử lý rất uyển chuyển trong Aoud Leather, chúng hòa quyện lại với nhau và bổ trợ qua lại một cách thú vị.', '2023-10-02',0.45, 1),

    ('MOSCHINO TOY BOY', 5, 1, '100ml', 59.99, 80, 25, 5, 0, 'images/products/Men/5-1.png, images/products/Men/5-2.png', 
	N'Toy Boy là dòng nước hoa nam của thương hiệu Moschino mới được ra mắt vào năm 2019 và được thiết kế bởi giám đốc sáng tạo Jeremy Scott. Toy Boy diễn giải về một người đàn ông tự tin, năng động, đầy hoài bão và đam mê, nhưng không ngại thể hiện khía cạnh hóm hỉnh và hài hước của bản thân.', '2023-05-02',0.7, 1),
   
    ('JAGUAR CLASSIC BLACK EDT', 6, 1, '100ml', 69.99, 60, 12, 5, 0, 'images/products/Men/6-1.png, images/products/Men/6-2.png', 
   N'Sau khi sử dụng, xịt nước hoa lên cơ thể, tránh dùng tay chà xát hoặc làm khô da bằng những vật dụng khác, điều này phá vỡ các tầng hương trong nước hoa, khiến nó có thể thay đổi mùi hương hoặc bay mùi nhanh hơn.', '2009-07-02',0.3, 1),


    ('CAROLINA HERRERA BAD BOY LE PARFUM', 7, 1, '100ml', 79.99, 90, 18, 4, 0,'images/products/Men/7-1.png, images/products/Men/7-2.png', 
	N'Carolina Herrera Bad Boy thể hiện rõ nét qua sự quyết đoán, không quá cầu kỳ. Hương thơm nam tính, ngọt ngào vương vấn khó quên đi cùng thiết kế mới lạ độc đáo chắc chắn sẽ khiến các cánh mày râu thích thú.' , '2023-02-02',0.45, 1),

    ('BURBERRY HERO EDP', 8, 1, '100ml', 89.99, 110, 22, 4, 0 ,'images/products/Men/8-1.png, images/products/Men/8-2.png',
	N'Tầng hương cuối được chế ngự bởi Gỗ là gỗ, Tuyết tùng với nét khô đanh đặc trưng, chầm chậm lan toả lên trên làn da, đượm lại một chút tĩnh lặng nơi khứu giác.' , '2022-02-02',0.5, 1),

    ('CALVIN KLEIN CK EVERYONE EDT', 9, 1, '100ml', 99.99, 150, 35, 3, 0,'images/products/Men/9-1.png, images/products/Men/9-2.png',
	N'Như một lựa chọn không thể thiếu với nam giới vào những ngày oi bức, Versace Pour Homme là mùi hương được rất được ưa chuộng cho các anh đang bắt đầu tìm cho mình một mùi hương dễ chịu, dễ gần và đa dụng.' , '2023-09-01',0.7, 1),

    ('JEAN PAUL LE BEAU LE PARFUM', 10, 1, '125ml', 109.99, 70, 14, 5, 0,'images/products/Men/10-1.png, images/products/Men/10-2.png', 
	N'Nhiều người tìm đến Creed Aventus không chỉ vì tiếng tăm, mùi hương xuất chúng, mà cũng vì độ đa dụng nữa. Creed Aventus có độ tươi ngọt của trái cây để tỏa sáng vào những ngày hè và cũng nhờ vào hương khói đặc trưng để có thêm độ ấm ấp và trấm lắng cho những ngày tiết trời se se lạnh.' , '2021-07-02',0.3, 1),
	--
	('JEAN PAUL GAULTIER LE MALE ON BOARD EDT', 2, 1, '100ml', 29.99, 100, 20, 4, 0, 'images/products/Men/11-1.png, images/products/Men/11-2.png', 
	N'Đôi nét về Jean Paul Gaultier Le Male On Board EDT: là mùi hương mang đến cho bạn cảm giác tươi mát, và yếu tố nam tính và mạnh mẽ của phái mạnh. Mở đầu bằng sự kết hợp giữa Hương biển, Tinh dầu lá chanh và Bạc hà, tổ hợp hương thơm trong tầng hương đầu khiến không gian trở nên trong lành và chắc hẳn tầng hương này sẽ giúp những chàng trai thể hiện phong cách phóng khoáng và năng động.', '2023-11-12',0.3, 1),

    ('LOUIS VUITTON CITY OF STARS', 1, 1, '100ml', 29.99, 100, 10, 3, 0, 'images/products/Men/12-1.png, images/products/Men/12-2.png', 
	N'LOUIS VUITTON CITY OF STARS : Vị ngọt làm nên tính cách của Jean Paul Gaultier Le Male Elixir đến từ Vanilla và Mật ong. Vanilla bông phấn mịn màng, quyện cùng Mật ong sánh đặc, tạo nên tông vị mềm mại nhưng vẫn da diết vô cùng.', '2023-11-04',0.3, 1),

    ('GIORGIO ARMANI ACQUA DI GIO PARFUM', 4, 1, '125ml 75ml', 39.99, 75, 15, 4, 0, 'images/products/Men/13-1.png, images/products/Men/13-2.png', 
	N'Mùi hương đặc trưng: mang đậm dấu ấn của phiên bản gốc 2006. Như thể Nauticađang kêu gọi chúng ta một lần nữa chèo trên con thuyền Gỗ và bắt lấy những con sóng xanh rì. Lấy cảm hứng từ tinh thần thể thao của những chàng trai, Nautica Voyage Sport công bố là một phiên bản mang đậm vẻ đẹp thể thao, năng động.', '2016-10-02',0.3, 1),

    ('PACO RABANNE PURE XS EDT', 3, 1, '125ml 75ml', 49.99, 120, 30, 2, 0, 'images/products/Men/14-1.png, images/products/Men/14-2.png', 
	N'PACO RABANNE PURE XS EDT khai thác nét đẹp của nốt hương Da thuộc, Trầm hương và Nhụy nghệ tây. Nói là các yếu tố mạnh mẽ, nhưng Da thuộc và Trầm hương lại được Montale xử lý rất uyển chuyển trong Aoud Leather, chúng hòa quyện lại với nhau và bổ trợ qua lại một cách thú vị.', '2023-10-02',0.45, 1),

    ('DOLCE GABBANA LIGHT BLUE SUMMER VIBES POUR HOMME EDT', 6, 1, '100ml 125ml', 59.99, 80, 25, 4, 0, 'images/products/Men/15-1.png, images/products/Men/15-2.png', 
	N'DOLCE GABBANA LIGHT BLUE SUMMER VIBES POUR HOMME EDT là dòng nước hoa nam của thương hiệu Moschino mới được ra mắt vào năm 2019 và được thiết kế bởi giám đốc sáng tạo Jeremy Scott. Toy Boy diễn giải về một người đàn ông tự tin, năng động, đầy hoài bão và đam mê, nhưng không ngại thể hiện khía cạnh hóm hỉnh và hài hước của bản thân.', '2023-05-02',0.7, 1),
   
   ('HUGO BOSS THE SCENT INTENSE', 5, 1, '100ml 125ml', 59.99, 60, 12, 3, 0, 'images/products/Men/16-1.png, images/products/Men/16-2.png', 
   N'Sau khi sử dụng, xịt nước hoa lên cơ thể, tránh dùng tay chà xát hoặc làm khô da bằng những vật dụng khác, điều này phá vỡ các tầng hương trong nước hoa, khiến nó có thể thay đổi mùi hương hoặc bay mùi nhanh hơn.', '2009-06-02',0.7, 1),


    ('YSL MYSLF EDP', 10, 1, '75ml 100ml', 29.99, 90, 18, 5, 0,'images/products/Men/17-1.png, images/products/Men/17-2.png', 
	N'YSL MYSLF EDP thể hiện rõ nét qua sự quyết đoán, không quá cầu kỳ. Hương thơm nam tính, ngọt ngào vương vấn khó quên đi cùng thiết kế mới lạ độc đáo chắc chắn sẽ khiến các cánh mày râu thích thú.' , '2023-02-02',0.7, 1),

    ('CHANEL ALLURE HOMME EDT', 9, 1, '100ml', 59.99, 110, 22, 3, 0 ,'images/products/Men/18-1.png, images/products/Men/18-2.png',
	N'Tầng hương cuối được chế ngự bởi Gỗ là gỗ, Tuyết tùng với nét khô đanh đặc trưng, chầm chậm lan toả lên trên làn da, đượm lại một chút tĩnh lặng nơi khứu giác.' , '2022-02-03',0.5, 1),

    ('LOUIS VUITTON PACIFIC CHILL EDP', 8, 1, '100ml 125ml', 79.99, 150, 35, 3, 0,'images/products/Men/19-1.png, images/products/Men/19-2.png',
	N'LOUIS VUITTON PACIFIC CHILL EDP: Như một lựa chọn không thể thiếu với nam giới vào những ngày oi bức, Versace Pour Homme là mùi hương được rất được ưa chuộng cho các anh đang bắt đầu tìm cho mình một mùi hương dễ chịu, dễ gần và đa dụng.' , '2023-11-01',0.7, 1),

    ('JULIETTE HAS A GUN ANOTHER OUD EDP', 7, 1, '75ml 125ml', 49.99, 70, 14, 2, 0,'images/products/Men/20-1.png, images/products/Men/20-2.png', 
	N'Nhiều người tìm đến Creed Aventus không chỉ vì tiếng tăm, mùi hương xuất chúng, mà cũng vì độ đa dụng nữa. Creed Aventus có độ tươi ngọt của trái cây để tỏa sáng vào những ngày hè và cũng nhờ vào hương khói đặc trưng để có thêm độ ấm ấp và trấm lắng cho những ngày tiết trời se se lạnh.' , '2021-07-12',0.3, 1),

	-- 20 products for women (https://missi.com.vn/danh-muc/nuoc-hoa-nu/)
	('KILIAN GOOD GIRL GONE BAD', 1, 2, '50ml', 19.99, 100, 20, 4, 0, 'images/products/Women/1-1.png, images/products/Women/1-2.png', 
	N'Kilian Good Girl Gone Bad mở đầu bằng hương Hoa Mộc tê, một loài hoa đáng yêu đến từ Châu Á, với mùi đặc trưng giống với quả mơ thơm. Tinh tế và ngây thơ, Hoa Mộc tê đem tới Good Girl Gone Bad chút âm hưởng mềm mại của Trái cây, hòa cùng Hoa Nhài e ấp dè dặt. Dần dần, sự hiền lành e ấp đó được kéo xuống, bởi bông Huệ gợi cảm, một nét gợi cảm da thịt đặc trưng của loài hoa ngả ngớn này.', '2023-05-01',0.7, 1),

    ('KILIAN LOVE DON’T BE SHY EDP', 2, 2, '50ml', 29.99, 50, 10, 5, 0,'images/products/Women/2-1.png, images/products/Women/2-2.png', 
	N'Nước hoa có thể bám tốt hay không tốt tùy thuộc vào thời gian, không gian, cơ địa, chế độ sinh hoạt, ăn uống của bạn, việc sử dụng một loại nước hoa trong thời gian dài có thể khiến bạn bị quen mùi, dẫn đến hiện tượng không nhận biết được mùi hương. Mang theo nước hoa bên mình hoặc trang bị những mẫu mini tiện dụng để giúp bản thân luôn tự tin mọi lúc mọi nơi.' , '2023-05-11',0.5, 1),

    ('LANCOME IDOLE L’INTENSE EDP', 3, 2, '75ml', 39.99, 75, 15, 4, 0,'images/products/Women/3-1.png, images/products/Women/3-2.png',
	N'Viên kim cương mang hương thơm của Xạ hương đến từ Lancome không lộng lẫy, choáng ngợp, mà mềm mại, quyến rũ và hiền hòa trên làn da phái đẹp.', '2019-09-08', 0.45, 1),

    ('GUCCI FLORA GORGEOUS MAGNOLIA', 4, 2, '70ml', 49.99, 120, 30, 4, 0,'images/products/Women/4-1.png, images/products/Women/4-2.png', 
	N'Dưới bàn tay ma thuật của nhà Gucci, Hoa mộc lan trong Gucci Flora Gorgeous Magnolia vẫn giữ được nét độc đáo như vậy, nhưng lần này lại nhu mì và hiền hơn đôi chút dưới cách tác hợp của Dừa ngọt béo và Hoa nhài quyến rũ. Chút Mâm xôi được thêm vào như một nét chấm phá, khiến tổng thể Gorgeous Magnolia có phần mọng nước và ranh mãnh hơn.', '2023-08-09',0.7, 1),

    ('GIORGIO ARMANI PRIVE ROSE DARABIE EDP INTENSE', 5, 2, '100ml', 59.99, 80, 25, 4, 0,'images/products/Women/5-1.png, images/products/Women/5-2.png', 
	N'Cuối cùng, Ocean di Gioia của Giorgio Armani thu mình lại, gói ghém những giá trị mùi hương vào vỏn vẹn 2 nốt hương xạ hương và gỗ đàn hương nhưng để lại trong lòng người mộ điệu những dư âm kéo dài miên man, lưu luyến.', '2020-08-11',0.3, 1),

    ('GUCCI A SONG FOR THE ROSE EDP', 6, 2, '100ml', 69.99, 60, 12, 5, 0,'images/products/Women/6-1.png, images/products/Women/6-2.png', 
	N'Là một mùi hương unisex, A Song For The Rose đem tới cho chúng ta một thức thơm trung tính. Nói đơn giản, nếu bạn yêu thích hương Hoa hồng gợi cảm, quyến rũ nhưng phi giới tính, muốn bản thân tỏa ra hương thơm như một bông hồng, thì Gucci A Song For The Rose là lựa chọn dành cho bạn.', '2023-01-11',0.45, 1),

    ('DIOR JOY EAU DE PARFUM INTENSE', 7, 2, '90ml', 79.99, 90, 18, 3, 0, 'images/products/Women/7-1.png, images/products/Women/7-2.png', 
	N'JOY by Dior, Eau de Parfum Intense là một hương thơm mới với sự bùng nổ của những bông hoa rạng rỡ, một vệt hương hoa vui tươi mang vẻ đẹp đa chiều. Ánh sáng chói lọi của nhà Cam Chanh (Cam Bergamot và Hoa Cam) ngon ngọt hòa quyện với ánh sáng rực rỡ đầy màu sắc của Hoa Hồng Grasse và hoa Nhài, được nâng cao bởi một âm hưởng gỗ bao bọc của Gỗ Đàn Hương và Xạ Hương đang nhuốm màu Vanilla.', '2019-02-11',0.5, 1),

    ('CAROLINA HERRERA VERY GOOD GIRL GLAM', 8, 2, '80ml', 89.99, 110, 22, 4, 0, 'images/products/Women/8-1.png, images/products/Women/8-2.png', 
	N'Tầng hương giữa lại đem đến một cảm giác ngọt ngào nhưng kiêu kỳ, toả sáng bởi sự kết hợp của những loài hoa khác nhau. Hương kem beo béo của Huệ hoà quyện với Hoa nhài Sambac tươi tắn, tất thảy làm nên hương thơm nữ tính nhưng không hề ủy mị, dịu dàng mà vẫn toát lên vẻ thanh lịch củaGood Girl. Và rồi tầng hương cuối nào là Đậu Tonka, Vanilla hay kẹo Praline làm cho hương thơm béo ngậy, trầm ấm, có chút thoảng Hương Gỗ đầy lắng đọng, vấn vương.', '2023-02-12',0.7, 1),

    ('JO MALONE ORANGE BLOSSOM EAU DE COLOGNE', 9, 2, '30ml', 99.99, 150, 35, 4, 0, 'images/products/Women/9-1.png, images/products/Women/9-2.png', 
	N'Dù có phần nhẹ nhàng và quyến rũ, nhưng dường như Calvin Klein Euphoria vẫn không thể phớt lờ cái tôi đầy kiêu kỳ, mãnh liệt. Bằng hơi thở đậm nét phương Đông; Hổ phách, xạ hương và gỗ gụ đã cuộn trào một luồng hương thơm cuồng nhiệt, tìm đến và chinh phục những tâm hồn vô tình chạm phải. Chính sự lan toả của note hương này đã tạo nên chiếc đòn bẫy khiến Calvin Klein Euphoria trở thành niềm kiêu hãnh của những bóng hồng.', '2010-12-08', 0.45, 1),

    ('CALVIN KLEIN CK EVERYONE EDT', 10, 2, '100ml', 109.99, 70, 14, 5, 0, 'images/products/Women/10-1.png, images/products/Women/10-2.png', 
	N'Dù có phần nhẹ nhàng và quyến rũ, nhưng dường như Calvin Klein Euphoria vẫn không thể phớt lờ cái tôi đầy kiêu kỳ, mãnh liệt. Bằng hơi thở đậm nét phương Đông; Hổ phách, xạ hương và gỗ gụ đã cuộn trào một luồng hương thơm cuồng nhiệt, tìm đến và chinh phục những tâm hồn vô tình chạm phải. Chính sự lan toả của note hương này đã tạo nên chiếc đòn bẫy khiến Calvin Klein Euphoria trở thành niềm kiêu hãnh của những bóng hồng.', '2020-07-12', 0.3, 1),

	--
	('LANCOME LA VIE EST BELLE EDP', 10, 2, '50ml 75ml', 39.99, 100, 20, 4, 0, 'images/products/Women/11-1.png, images/products/Women/11-2.png', 
	N'LANCOME LA VIE EST BELLE EDP mở đầu bằng hương Hoa Mộc tê, một loài hoa đáng yêu đến từ Châu Á, với mùi đặc trưng giống với quả mơ thơm. Tinh tế và ngây thơ, Hoa Mộc tê đem tới Good Girl Gone Bad chút âm hưởng mềm mại của Trái cây, hòa cùng Hoa Nhài e ấp dè dặt. Dần dần, sự hiền lành e ấp đó được kéo xuống, bởi bông Huệ gợi cảm, một nét gợi cảm da thịt đặc trưng của loài hoa ngả ngớn này.', '2023-07-01',0.7, 1),

    ('JEAN PAUL CLASSIQUE 100ML EDP', 9, 2, '100ml', 29.99, 50, 10, 4, 0,'images/products/Women/12-1.png, images/products/Women/12-2.png', 
	N'Nước hoa có thể bám tốt hay không tốt tùy thuộc vào thời gian, không gian, cơ địa, chế độ sinh hoạt, ăn uống của bạn, việc sử dụng một loại nước hoa trong thời gian dài có thể khiến bạn bị quen mùi, dẫn đến hiện tượng không nhận biết được mùi hương. Mang theo nước hoa bên mình hoặc trang bị những mẫu mini tiện dụng để giúp bản thân luôn tự tin mọi lúc mọi nơi.' , '2023-05-10',0.5, 1),

    ('JEAN PAUL GAULTIER LA BELLE', 8, 2, '75ml 100ml', 39.99, 75, 15, 5, 0,'images/products/Women/13-1.png, images/products/Women/13-2.png',
	N'Viên kim cương mang hương thơm của Xạ hương đến từ Lancome không lộng lẫy, choáng ngợp, mà mềm mại, quyến rũ và hiền hòa trên làn da phái đẹp.', '2019-09-08', 0.3, 1),

    ('GUCCI BLOOM PROFUMO DI FIORI', 7, 2, '5ml 70ml 100ml', 49.99, 120, 30, 4, 0,'images/products/Women/14-1.png, images/products/Women/14-2.png', 
	N'Dưới bàn tay ma thuật của nhà Gucci, Hoa mộc lan trong Gucci Flora Gorgeous Magnolia vẫn giữ được nét độc đáo như vậy, nhưng lần này lại nhu mì và hiền hơn đôi chút dưới cách tác hợp của Dừa ngọt béo và Hoa nhài quyến rũ. Chút Mâm xôi được thêm vào như một nét chấm phá, khiến tổng thể Gorgeous Magnolia có phần mọng nước và ranh mãnh hơn.', '2023-08-09',0.3, 1),

    ('YSL LIBRE L ABSOLU PLATINE', 6, 2, '90ml', 39.99, 80, 25, 3, 0,'images/products/Women/15-1.png, images/products/Women/15-2.png', 
	N'Cuối cùng, Ocean di Gioia của Giorgio Armani thu mình lại, gói ghém những giá trị mùi hương vào vỏn vẹn 2 nốt hương xạ hương và gỗ đàn hương nhưng để lại trong lòng người mộ điệu những dư âm kéo dài miên man, lưu luyến.', '2020-08-11',0.3, 1),

    ('VERSACE POUR FEMME DYLAN PURPLE', 4, 2, '5ml 100ml', 49.99, 60, 12, 2, 0,'images/products/Women/16-1.png, images/products/Women/16-2.png', 
	N'VERSACE POUR FEMME DYLAN PURPLE là một mùi hương unisex, A Song For The Rose đem tới cho chúng ta một thức thơm trung tính. Nói đơn giản, nếu bạn yêu thích hương Hoa hồng gợi cảm, quyến rũ nhưng phi giới tính, muốn bản thân tỏa ra hương thơm như một bông hồng, thì Gucci A Song For The Rose là lựa chọn dành cho bạn.', '2023-01-11',0.45, 1),

    ('VICTORIA’S SECRET TEASE FLOWER EDP', 5, 2, '100ml', 79.99, 90, 18, 3, 0, 'images/products/Women/17-1.png, images/products/Women/17-2.png', 
	N'VICTORIA’S SECRET TEASE FLOWER EDP là một hương thơm mới với sự bùng nổ của những bông hoa rạng rỡ, một vệt hương hoa vui tươi mang vẻ đẹp đa chiều. Ánh sáng chói lọi của nhà Cam Chanh (Cam Bergamot và Hoa Cam) ngon ngọt hòa quyện với ánh sáng rực rỡ đầy màu sắc của Hoa Hồng Grasse và hoa Nhài, được nâng cao bởi một âm hưởng gỗ bao bọc của Gỗ Đàn Hương và Xạ Hương đang nhuốm màu Vanilla.', '2019-02-11',0.5, 1),

    ('JO MALONE LONDON BLACKBERRY & BAY COLOGNE', 3, 2, '80ml', 89.99, 110, 22, 4, 0, 'images/products/Women/18-1.png, images/products/Women/18-2.png', 
	N'Tầng hương giữa lại đem đến một cảm giác ngọt ngào nhưng kiêu kỳ, toả sáng bởi sự kết hợp của những loài hoa khác nhau. Hương kem beo béo của Huệ hoà quyện với Hoa nhài Sambac tươi tắn, tất thảy làm nên hương thơm nữ tính nhưng không hề ủy mị, dịu dàng mà vẫn toát lên vẻ thanh lịch củaGood Girl. Và rồi tầng hương cuối nào là Đậu Tonka, Vanilla hay kẹo Praline làm cho hương thơm béo ngậy, trầm ấm, có chút thoảng Hương Gỗ đầy lắng đọng, vấn vương.', '2023-02-12',0.7, 1),

    ('BVLGARI OMNIA GOLDEN CITRINE EDT', 2, 2, '30ml 75ml, 100ml', 99.99, 150, 35, 5, 0, 'images/products/Women/19-1.png, images/products/Women/19-2.png', 
	N'Dù có phần nhẹ nhàng và quyến rũ, nhưng dường như Calvin Klein Euphoria vẫn không thể phớt lờ cái tôi đầy kiêu kỳ, mãnh liệt. Bằng hơi thở đậm nét phương Đông; Hổ phách, xạ hương và gỗ gụ đã cuộn trào một luồng hương thơm cuồng nhiệt, tìm đến và chinh phục những tâm hồn vô tình chạm phải. Chính sự lan toả của note hương này đã tạo nên chiếc đòn bẫy khiến Calvin Klein Euphoria trở thành niềm kiêu hãnh của những bóng hồng.', '2010-12-10', 0.45, 1),

    ('BVLGARI SPLENDIDA PATCHOULI TENTATION EDP', 1, 2, '100ml', 109.99, 70, 14, 4, 0, 'images/products/Women/20-1.png, images/products/Women/20-2.png', 
	N'BVLGARI SPLENDIDA PATCHOULI TENTATION EDPD có phần nhẹ nhàng và quyến rũ, nhưng dường như Calvin Klein Euphoria vẫn không thể phớt lờ cái tôi đầy kiêu kỳ, mãnh liệt. Bằng hơi thở đậm nét phương Đông; Hổ phách, xạ hương và gỗ gụ đã cuộn trào một luồng hương thơm cuồng nhiệt, tìm đến và chinh phục những tâm hồn vô tình chạm phải. Chính sự lan toả của note hương này đã tạo nên chiếc đòn bẫy khiến Calvin Klein Euphoria trở thành niềm kiêu hãnh của những bóng hồng.', '2005-07-12', 0.3, 1),

	-- 10 products for unisex (https://missi.com.vn/danh-muc/nuoc-hoa-phap/)

	('KILIAN CAN’T STOP LOVING YOU', 1, 4, '50ml', 19.99, 100, 20, 4, 0, 'images/products/Unisex/1-1.png, images/products/Unisex/1-2.png', 
	N'Sự ấm áp và nồng nàn từ những môi hôn trao nhau, Xạ Hương Trắng xuất hiện như một đốm lửa loe lói nhưng vô cùng mãnh liệt minh chứng cho tình yêu sâu thẳm cùng những cảm xúc khiến cả hai trở nên hòa hợp mãi mãi.','2023-02-11', 0.7, 1),
		
    ('NAUTICA VOYAGE N-83 EDT', 2, 4, '100ml', 29.99, 50, 10, 3, 0, 'images/products/Unisex/2-1.png, images/products/Unisex/2-2.png', 
	N'Bỏ lại sau lưng là những thử thách anh đã vượt qua, một chút mùi hương từ Cây Cúc Đại Đóa như một bản tuyên ngôn khẳng định những mong muốn cũng như mục tiêu mà anh ta sẽ đánh đổi cả bản thân mình để đạt được.','2022-01-12', 0.45, 1),

    ('JEAN PAUL GAULTIER LE MALE ELIXIR', 3, 4, '75ml', 39.99, 75, 15, 5, 0,'images/products/Unisex/3-1.png, images/products/Unisex/3-2.png', 
	N'Cũng như các dòng nước hoa khác của thương hiệu Jean Paul Gaultier, Le Beau cũng là một ứng cử viên sáng giá cho những dịp lên đồ đi vào những nơi đông người như Bar, Pub. Hương thơm ngọt ngào và đầy cuốn hút cùng với độ tỏa mùi tốt của Le Beau chắc chắn mang lại cho anh chàng không những ánh nhìn đầy tò mò mà còn những lời khen ngợi.','2023-01-14', 0, 1),

    ('KILIAN LOVE DON’T BE SHY EDP', 4, 4, '50ml', 49.99, 120, 30, 5, 0,'images/products/Unisex/4-1.png, images/products/Unisex/4-2.png', 
	N'Absinthe không trầm tĩnh, mà ngược lại vô cùng sinh động với tông vị ngọt ngào dễ chịu, thế nhưng ấn tượng nó gây ra không dễ thương một xíu nào. Còn vì sao thì bạn hãy cứ thử một lần để phần nào có cho mình trải nghiệm. Ẩn sau đó là chút hương thảo mộc, hơi khô nhưngkhông hề thô của Cam thảo, quyện cùng chút ngai ngái, tươi mới của Lá hoa tím.','2020-11-12', 0.3, 1),

    ('LANCOME IDOLE L’INTENSE EDP', 5, 4, '75ml', 59.99, 80, 25, 5, 0, 'images/products/Unisex/5-1.png, images/products/Unisex/5-2.png', 
	N'Cuối cùng, sự nồng nàn từ tầng hương cuối cùng khiến cô nổi bật nhất giữa chốn đông người, mùi hương từ cây Hoắc Hương và Đậu Tonka khiến người ta phải chủ động hướng về cô như một cách hút hồn ma mị của một loại bùa ngải mang tên Lancome La Vie Est Belle.','2023-12-09', 0.7, 1),

	('LANCOME HYPNOSE', 5, 4, '75ml', 59.99, 80, 25, 5, 0, 'images/products/Unisex/6-1.png, images/products/Unisex/6-2.png', 
	N'Hypnose của Lancome là một mùi hương theo hướng phương Đông đầy nắng, rừng với sắc thái sành điệu. Hương thơm nổi bật với hoa lạc tiên, tạo nên sự dịu dàng và nồng nàn của nữ giới. Sản phẩm kỳ diệu của hương hoa - vani có mùi thơm và ấm áp trên da và cỏ vetiver được bao quanh bởi hoa trắng và hoa nhài Sambac mang đến cảm giác gợi cảm và sâu lắng.','2023-03-09', 0.5, 1),	
	--
	('CAROLINA HERRERA BAD BOY LE PARFUM', 7, 4, '100ml', 79.99, 90, 18, 4, 0,'images/products/Unisex/7-1.png, images/products/Unisex/7-2.png', 
	N'Carolina Herrera Bad Boy thể hiện rõ nét qua sự quyết đoán, không quá cầu kỳ. Hương thơm nam tính, ngọt ngào vương vấn khó quên đi cùng thiết kế mới lạ độc đáo chắc chắn sẽ khiến các cánh mày râu thích thú.' , '2023-02-02',0.45, 1),

    ('PACO RABANNE 1 MILLION PARFUM', 8, 4, '100ml', 20.15, 110, 22, 4, 0 ,'images/products/Unisex/8-1.png, images/products/Unisex/8-2.png',
	N'Tầng hương cuối được chế ngự bởi Gỗ là gỗ, Tuyết tùng với nét khô đanh đặc trưng, chầm chậm lan toả lên trên làn da, đượm lại một chút tĩnh lặng nơi khứu giác.' , '2022-02-02',0.5, 1),

    ('KLEIN CK EVERYONE EDT', 9, 4, '80ml', 99.99, 150, 35, 2, 0,'images/products/Unisex/9-1.png, images/products/Unisex/9-2.png',
	N'Như một lựa chọn không thể thiếu với nam giới vào những ngày oi bức, Versace Pour Homme là mùi hương được rất được ưa chuộng cho các anh đang bắt đầu tìm cho mình một mùi hương dễ chịu, dễ gần và đa dụng.' , '2023-09-01',0.7, 1),

	('PACO RABANNE FAME', 7, 4, '90ml', 79.99, 90, 18, 3, 0, 'images/products/Unisex/10-1.png, images/products/Unisex/10-2.png', 
	N'Ngay khi làn hương ấy chạm lên da, bạn sẽ bất ngờ vì hương Xoài nồng nàn, thơm phức, nó như hấp thụ hết thảy tinh hoa của đất đồng mà trở nên mọng nước, dịu ngọt vô cùng. Không chỉ thế, nhà mốt rất biết cách thưởng ngoạn người dùng vì đan xen những nốt Đàn hương trong cái mọng nước của mùa Xoài chín. Mùi hương mang phong vị phương Đông với đặc trưng đậm vị kem sữa, ngọt,ấm và balsamic. Có lẽ cái hay nhất của Paco Rabanne Fame là cái tinh hoa cuối cùng đọng lại trên làn da.', '2019-02-11',0.5, 1),

    ('BVLGARI BLV POUR HOMME', 8, 4, '80ml', 89.99, 110, 22, 4, 0, 'images/products/Unisex/11-1.png, images/products/Unisex/11-2.png', 
	N'Mang tinh thần của một chàng trai lịch lãm nhưng kín đáo. BLV Pour Homme như một điểm sáng cho bất cứ người đàn ông nào sở hữu nó-loại vũ khí thanh lịch. Ra đời bởi Alberto Morillas vào năm 2001, BVL Pour homme được sáng tạo một cách tương phản nhưng lại hòa huyện một cách hoàn hảo trong từng nốt hương.Mở đầu với một loại hỗn hợp ấm áp của bạch đậu khấu và gỗ đàn hương, nó không gắt gỏng vồ vập, nhưng lại mang đến sự nhẹ nhàng, tinh tế.', '2023-02-12',0.7, 1),

	-- 10 products kids (https://missi.com.vn/danh-muc/nuoc-hoa-niche/)
	('LOUIS VUITTON ROSE DES VENTS', 1, 3, '50ml', 19.99, 100, 20, 5, 0,'images/products/Kids/1-1.png, images/products/Kids/1-2.png', 
	N'Lôi cuốn và thu hút mọi người bằng sự tinh tế nhưng cũng không kém phần đơn giản, những nốt hương sang trọng từ Da thuộc và Trầm hương hiện lên như một điểm đánh đấu sự đặc trưng của toàn bộ tầng hương bên trong. Bay bổng hơn một chút cùng với hương Nhang, những nốt Nhũ hương và Xạ hương xuất hiện như đôi cánh giúp hương thơm của Louis Vuitton Nuit de Feu lan tỏa đi khắp mọi nơi xung quanh mà nó đi tới.', '2009-03-11', 0.7, 1),

    ('LE LABO BERGAMOTE 22 EDP', 2, 3, '50ml', 29.99, 50, 10, 2, 0,'images/products/Kids/2-1.png, images/products/Kids/2-2.png', 
	N'Tuy nói là mạnh mẽ, thế nhưng vẫn phải đính chính lại rằng The Matcha 26 vẫn giữ nguyên vẹn sự thanh thoát, pha chút ngon ngót đặc trưng của Matcha. Chỉ khác rằng xung quanh mùi hương ấy được bao bọc bởi Quả sung, Cam đắng, Cỏ hương bài và Tuyết tùng. Tất cả vừa cho thêm vào chút chua ngọt của quả mọng, vừa cho thêm chút khô ráp của Gỗ đê hoà quyện với hương thơm của Matcha.',  '2023-04-12', 0.3, 1),

    ('CREED SILVER MOUNTAIN WATER', 3, 3, '20ml', 39.99, 75, 15, 4, 0, 'images/products/Kids/3-1.png, images/products/Kids/3-2.png', 
	N'Đây có lẽ là một mùi hương tốt của Creed, dù cho có bị những cái tên nổi danh khác như Green Irish Tweed hay Aventus che mờ bớt chút, nhưng Silver Mountain Water vẫn sẽ mãi thỏa long được người dùng bất kể khi nào, một hương thơm vượt thời gian.', '2023-12-05', 0.45, 1),

    ('CALVIN KLEIN CK ONE TRAVEL SIZE', 4, 3, '15ml', 49.99, 120, 30, 4, 0, 'images/products/Kids/4-1.png, images/products/Kids/4-2.png', 
	N' CK khiến khứu giác trở nên thư giãn và sạch tới bất ngờ. Midnote của CK one là một chợ hoa đêm, với Hoa tím, Hoa nhài hay Hoa linh lan thung lũng, một sự cuốn hút và táo bạo của nhà CK khi đặt trái cây và hương hoa vào cùng với nhau. Tạo nên cảm giác giải nhiệt nhưng đủ gây sự chú ý. Kết thúc bằng hương Gỗ tuyết tùng và Gỗ đàn hương, CK trọn vẹn mùi hương trên da, trên quần áo bạn và sẵn sàng giúp bạn tự tin cho một ngày mới, hãy là số 1, đừng là số 2 với CK one.' ,  '2023-11-12', 0.3, 1),

    ('MARC JACOBS DAISY DREAM MINI SIZE', 5, 3, '15ml', 59.99, 80, 25, 4, 0, 'images/products/Kids/5-1.png, images/products/Kids/5-2.png',
	N'Marc Jacobs Daisy Dream là vậy, tuy vẫn là cô nàng trẻ trung và mộng mơ nhưng lại có thừa bản lĩnh của tuổi trưởng thành.', '2023-09-12', 0, 1),
	--
	('LACOSTE EAU DE LACOSTE L.12.12 BLANC PURE', 1, 3, '50ml', 19.99, 100, 20, 4, 0, 'images/products/Kids/6-1.png, images/products/Kids/6-2.png', 
	N'Nổi tiếng toàn thế giới với dòng áo Polo thể thao đặc trưng, nhà Lacoste đã tham gia một sân chơi lớn hơn khi cho ra mắt dòng nước hoa cho nam đầu tiên, và để tạo nên thành công ấy,không thể không kể đến Lacoste White.', '2023-05-01', 0.0, 1),

    ('KILIAN LOVE DON’T BE SHY EDP', 2, 3, '50ml', 29.99, 50, 10, 5, 0,'images/products/Kids/7-1.png, images/products/Kids/7-2.png', 
	N'Nước hoa có thể bám tốt hay không tốt tùy thuộc vào thời gian, không gian, cơ địa, chế độ sinh hoạt, ăn uống của bạn, việc sử dụng một loại nước hoa trong thời gian dài có thể khiến bạn bị quen mùi, dẫn đến hiện tượng không nhận biết được mùi hương. Mang theo nước hoa bên mình hoặc trang bị những mẫu mini tiện dụng để giúp bản thân luôn tự tin mọi lúc mọi nơi.' , '2023-05-12',0.5, 1),

    ('CALVIN KLEIN CK ONE GOLD', 3, 3, '75ml', 39.99, 75, 15, 4, 0,'images/products/Kids/8-1.png, images/products/Kids/8-2.png',
	N'Nếu ai đó đã từng là fan trung thành với mùi hương của CK one, hẳn sẽ rất vui mừng khi nhà CK ưu ái cho ra đời một phiên bản mới với cái tên CK one Gold. Hiện đại hơn, cá tính hơn, và phủ lên đó thứ cảm xúc bằng Vàng', '2019-09-08', 0.45, 1),

	 ('CALVIN KLEIN CK FREE', 9, 3, '100ml', 59.99, 110, 22, 3, 0 ,'images/products/Kids/9-1.png, images/products/Kids/9-2.png',
	N'Nốt hương đầu tiên là những hương thơm Thảo mộc mát mẻ, hương xanh trong Ngải cứu đem lại một cảm giác khoan khoái, nhẹ nhàng bất tận. Nhưng đó chưa hẳn là mùi hương cốt yếu, cái hay của CK Free là sự hoà quyện độc đáo giữa Da lộn và mùi của Gỗ.' , '2022-02-03',0.5, 1),

    ('LOUIS VUITTON PACIFIC CHILL EDP', 8, 3, '100ml 125ml', 79.99, 150, 35, 3, 0,'images/products/Kids/10-1.png, images/products/Kids/10-2.png',
	N'LOUIS VUITTON PACIFIC CHILL EDP: Như một lựa chọn không thể thiếu với nam giới vào những ngày oi bức, Versace Pour Homme là mùi hương được rất được ưa chuộng cho các anh đang bắt đầu tìm cho mình một mùi hương dễ chịu, dễ gần và đa dụng.' , '2023-07-01',0.7, 1),

	-- 7 gift set products (https://missi.com.vn/danh-muc/gift-set/)
    ('GIFTSET MISSI LOVE SHOT', 1, 5, '100ml', 9.99, 50, 20, 5, 0, 'images/products/Gift/1-1.png, images/products/Gift/1-2.png',
	N'Giftset Missi Love Shot gồm 3 mùi đỉnh nhất hiện nay là Delina Parfum De Marly EDP, Le Labo Another 13, Narciso Pure Musc, chai 2ml có vòi xịt.', '2023-04-12',0, 1),

	('SET YSL Y EDP', 2, 5, '100ml', 19.99, 100, 20, 5, 0, 'images/products/Gift/2-1.png, images/products/Gift/2-2.png',
	N'Set bao gồm: 1 nước hoa fullsize 100ml ,1 chai mini 10ml dạng xịt bỏ túi cực xịn sò', '2023-05-11',0, 1),

	('GIFTSET MISSI ONE MORE', 3, 5, '100ml', 14.99, 90, 15, 5, 0, 'images/products/Gift/3-1.png, images/products/Gift/3-2.png',
	N'Giftset Missi One More gồm 3 mùi hot nhất của nam hiện nay là: Tomford Ombre Leather, Dior Sauvage Elixir, Chanel Bleu EDP, có 3 chai xịt 2ml.', '2022-12-11',0, 1),

	('SET DIOR NỮ 3 CHAI 5ML – JADORE, JOY, MISS DIOR', 4, 5, '3 chai 5ml', 9.99, 50, 22, 5, 0, 'images/products/Gift/4-1.png, images/products/Gift/4-2.png',
	N'Set Dior 3 chai chắn cũng sẽ là sự lựa chọn vô cùng tuyệt vời để làm quà tặng cho bạn bè hay người ấy bởi thiết kế vô cùng sang trọng và nhiều mùi nước hoa để chọn lựa cho đi chơi, đi làm hay đi tiệc đều được.', '2023-06-12',0, 1),

	('SET BVLGARI 3 CHAI 15ML', 5, 5, '5ml', 19.99, 80, 25, 5, 0, 'images/products/Gift/5-1.png, images/products/Gift/5-2.png',
	N'Bộ quà tặng Bvlgari Omnia gồm 3 sản phẩm: Nước hoa Bvlgari Omnia Coral EDT 15ml, Nước hoa Bvlgari Omnia Crystalline EDT 15ml, Nước hoa Bvlgari Omnia Amethyste EDT 15ml', '2023-09-12',0, 1),

	('SET SCANDAL POUR HOMME', 6, 5, '100ml 150ml', 17.99, 70, 30, 5, 0, 'images/products/Gift/6-1.png, images/products/Gift/6-2.png',
	N'Jean Paul Gaultier Scandal Pour Homme EDT thuộc tông mùi hương ngọt đặc trưng của nước hoa dòng thương hiệu Jean Paul Gaultier. Ngay trong lần xịt đầu tiên, bạn sẽ cảm nhận được mùi hương chua nhẹ nhàng tươi mát của cam quýt. Kết hợp cùng mùi hương tựa thảo mộc, nốt hương xanh của xô thơm. Hương thơm hoà vào nhau đánh thức khứu giác của bạn bởi mùi hương vô cùng nịnh mũi. ', '2022-07-12',0, 1),

	(N'SET 5 NƯỚC HOA LANCOME PARIS', 6, 5, '5ml 7.5ml 4ml 5ml 5ml', 17.99, 70, 30, 5, 0, 'images/products/Gift/7-1.png, images/products/Gift/7-2.png',
	N'Bộ Set 5 nước hoa Lancome bao gồm: Nước hoa Lancome Hypnose Eau de Parfum 5ml, Nước hoa Lancome Tresor Eau de Parfum 7.5ml, Nước hoa Lancome La vie est belle Eau de Parfum 4ml, Nước hoa Lancome Miracle Eau de Parfum 5ml, Nước hoa Lancome Tresor in Love Eau de Parfum 5ml ', '2023-08-12',0, 1);